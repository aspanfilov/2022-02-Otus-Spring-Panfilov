package ru.otus.spring.service;

import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

import java.util.List;

public class TestProcessor {
    private final QuestionService questionService;
    private final IOService ioService;

    private static final String EXIT_COMMAND = "q";
    private static final String HELP_COMMAND = "h";

    public TestProcessor(QuestionService questionService, IOService ioService) {
        this.questionService = questionService;
        this.ioService = ioService;
    }

    public void start() {
        var questions = this.questionService.getAll();
        var user = initializeUser();
        //todo help
        var testResult = askQuestions(user, questions);
        printResult(user, testResult);
    }

    private User initializeUser() {
        this.ioService.outputString("Enter name: ");
        User user = new User(this.ioService.readString());
        this.ioService.outputString(String.format("Hello, %s", user.getName()));
        return user;
    }

    private TestResult askQuestions(User user, List<Question> questions) {
        TestResult testResult = new TestResult(user, questions);

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

}
