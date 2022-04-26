package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;
import ru.otus.spring.exception.QuestionCreationException;
import ru.otus.spring.exception.QuestionSourceException;

import java.util.List;

@Service
public class TestProcessor {
    private final QuestionService questionService;
    private final QuestionInterpretator questionInterpretator;
    private final UserService userService;
    private final TestResultService testResultService;
    private final IOService ioService;

    private static final String EXIT_COMMAND = "q";
    private static final String HELP_COMMAND = "h";

    public TestProcessor(QuestionService questionService,
                         QuestionInterpretator questionInterpretator,
                         UserService userService,
                         TestResultService testResultService,
                         IOService ioService) {
        this.questionService = questionService;
        this.questionInterpretator = questionInterpretator;
        this.userService = userService;
        this.testResultService = testResultService;
        this.ioService = ioService;
    }

    public void start() {
        try {

            var questions = this.questionService.getAll();
            var user = userService.getUser();
            var testResult = askQuestions(questions);
            printResult(testResult, user);

        } catch (QuestionSourceException  e) {
            ioService.outputString("Ошибка чтения файла");
        } catch (NumberFormatException | QuestionCreationException e) {
            ioService.outputString("Ошибка считывания очередного вопроса из файла");
        } catch (ArithmeticException e) {
            ioService.outputString("Ошибка расчета результата тестирования");
        }
    }

    private TestResult askQuestions(List<Question> questions) {
        TestResult testResult = new TestResult(questions);

        this.ioService.outputString(HelpProvider.getInstruction());
        this.ioService.outputString("The test has started...");

        for (Question question : questions) {
            String userAnswer = askQuestionAndReturnAnswer(question);

            if (userAnswer.equalsIgnoreCase(EXIT_COMMAND)) {
                return testResult;
            }

            this.testResultService.saveAnswer(testResult, question, userAnswer);
        }
        return testResult;
    }

    private String askQuestionAndReturnAnswer(Question question) {

        List<String> availableInputAnswers = this.questionInterpretator.getAvailableInputAnswers(question);
        String inputAnswer = "";

        boolean isAnswerAdopted = false;
        while (!isAnswerAdopted) {
            ioService.outputString(questionInterpretator.getQuestionPhrase(question));
            ioService.outputString(questionInterpretator.getAnswerVariants(question));

            inputAnswer = ioService.readString();

            if (availableInputAnswers.contains(inputAnswer)) {
                isAnswerAdopted = true;
            } else if (inputAnswer.equalsIgnoreCase(EXIT_COMMAND)) {
                isAnswerAdopted = true;
            } else if (inputAnswer.equalsIgnoreCase(HELP_COMMAND)) {
                ioService.outputString(HelpProvider.getInstruction());
            } else {
                ioService.outputString("An unreadable answer has been entered. Try again. ");
            }
        }

        return inputAnswer;
    }

    private void printResult(TestResult testResult, User user) {
        this.ioService.outputString(
                this.testResultService.getResultMessage(testResult, user));
    }

}
