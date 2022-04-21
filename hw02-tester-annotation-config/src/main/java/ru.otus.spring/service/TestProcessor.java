package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

import java.util.List;

@Service
public class TestProcessor {
    private final QuestionService questionService;
    private final QuestionProvider questionProvider;
    private final UserService userService;
    private final IOService ioService;
    private final int passingPercentage;

    private static final String EXIT_COMMAND = "q";
    private static final String HELP_COMMAND = "h";

    public TestProcessor(QuestionService questionService,
                         QuestionProvider questionProvider,
                         UserService userService,
                         IOService ioService,
                         @Value("${passingPercentage}") int passingPercentage) {
        this.questionService = questionService;
        this.questionProvider = questionProvider;
        this.userService = userService;
        this.ioService = ioService;
        this.passingPercentage = passingPercentage;
    }

    public void start() {
        var questions = this.questionService.getAll();
        var user = userService.getUser();
        var testResult = askQuestions(questions);
        printResult(user, testResult);
    }

    private TestResult askQuestions(List<Question> questions) {
        TestResult testResult = new TestResult(questions);

        this.ioService.outputString(getHelp());
        this.ioService.outputString("The test has started...");

        for (Question question : questions) {
            String userAnswer = askQuestionAndReturnAnswer(question);

            if (userAnswer.equalsIgnoreCase(EXIT_COMMAND)) {
                return testResult;
            }

            saveUserAnswer(testResult, question, Integer.parseInt(userAnswer) - 1);
        }
        return testResult;
    }

    private void saveUserAnswer(TestResult testResult, Question question, int userAnswerId) {
        testResult.putAnswer(
                question,
                question.getAnswer(userAnswerId));
    }

    private String askQuestionAndReturnAnswer(Question question) {

        List<String> availableInputAnswers = this.questionProvider.getAvailableInputAnswers(question);
        String inputAnswer = "";

        boolean isAnswerAdopted = false;
        while (!isAnswerAdopted) {
            ioService.outputString(questionProvider.getQuestionPhrase(question));
            ioService.outputString(questionProvider.getAnswerVariants(question));

            inputAnswer = ioService.readString();

            if (availableInputAnswers.contains(inputAnswer)) {
                isAnswerAdopted = true;
            } else if (inputAnswer.equalsIgnoreCase(EXIT_COMMAND)) {
                isAnswerAdopted = true;
            } else if (inputAnswer.equalsIgnoreCase(HELP_COMMAND)) {
                ioService.outputString(getHelp());
            } else {
                ioService.outputString("An unreadable answer has been entered. Try again. ");
            }
        }

        return inputAnswer;
    }

    private String getHelp() {
        return "Instructions: \n"
                + "     To complete the test early, enter 'q'\n"
                + "     To call the instructions, enter 'h'\n"
                + "     To answer the question, enter the number of answer";
    }

    private void printResult(User user, TestResult testResult) {
        String msg =
                user.getName()
                + ", you have answered " + testResult.getNumberOfCorrectAnswers()
                + " out of " + testResult.getNumberOfQuestions()
                + " questions \n"
                + getPassingMessage(testResult);

        this.ioService.outputString(msg);
    }

    private String getPassingMessage(TestResult testResult) {
        int percent = testResult.getNumberOfCorrectAnswers() * 100 / testResult.getNumberOfQuestions();
        if (percent >= this.passingPercentage) {
            return "PASSED";
        } else {
            return "MISSED";
        }
    }
}
