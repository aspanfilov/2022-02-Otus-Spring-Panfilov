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
    private final HelpProvider helpProvider;
    private final IOService ioService;
    private final LocalizedOutputService localizedOutputService;

    private static final String EXIT_COMMAND = "q";
    private static final String HELP_COMMAND = "h";

    public TestProcessor(QuestionService questionService,
                         QuestionInterpretator questionInterpretator,
                         UserService userService,
                         TestResultService testResultService,
                         HelpProvider helpProvider,
                         IOService ioService,
                         LocalizedOutputService localizedOutputService) {
        this.questionService = questionService;
        this.questionInterpretator = questionInterpretator;
        this.userService = userService;
        this.testResultService = testResultService;
        this.helpProvider = helpProvider;
        this.ioService = ioService;
        this.localizedOutputService = localizedOutputService;
    }


    public void start() {
        try {

            var questions = this.questionService.getAll();
            var user = userService.getUser();
            var testResult = askQuestions(questions);
            printResult(testResult, user);

        } catch (QuestionSourceException  e) {
            this.localizedOutputService.outputMessage("errors.fileReadingError");
        } catch (NumberFormatException | QuestionCreationException e) {
            this.localizedOutputService.outputMessage("errors.questionReadingError");
        } catch (ArithmeticException e) {
            this.localizedOutputService.outputMessage("errors.testResultCalculatingError");
        }
    }

    private TestResult askQuestions(List<Question> questions) {
        TestResult testResult = new TestResult(questions);

        this.ioService.outputString(helpProvider.getInstruction());
        this.localizedOutputService.outputMessage("test.start");

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
            this.ioService.outputString(questionInterpretator.getQuestionPhrase(question));
            this.ioService.outputString(questionInterpretator.getAnswerVariants(question));

            inputAnswer = this.ioService.readString();

            if (availableInputAnswers.contains(inputAnswer)) {
                isAnswerAdopted = true;
            } else if (inputAnswer.equalsIgnoreCase(EXIT_COMMAND)) {
                isAnswerAdopted = true;
            } else if (inputAnswer.equalsIgnoreCase(HELP_COMMAND)) {
                this.ioService.outputString(helpProvider.getInstruction());
            } else {
                this.localizedOutputService.outputMessage("errors.unreadableAnswer");
            }
        }

        return inputAnswer;
    }

    private void printResult(TestResult testResult, User user) {
        this.ioService.outputString(
                this.testResultService.getResultMessage(testResult, user));
    }

}
