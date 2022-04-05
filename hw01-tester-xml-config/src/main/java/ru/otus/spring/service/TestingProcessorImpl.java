package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestingProcessorImpl implements TestingProcessor {

    private final TestingDataLoader testingDataLoader;
    private final QuestionProvider questionProvider;
    private final IOService ioService;
    private final TestingResultHandler testingResultHandler;

    private final Map<Question, Integer> testingData = new HashMap<>();
    private User user;

    private static final String EXIT_COMMAND = "q";
    private static final String HELP_COMMAND = "h";

    public TestingProcessorImpl(TestingDataLoader testingDataLoader,
                                QuestionProvider questionProvider,
                                TestingResultHandler testingResultHandler,
                                IOService ioService) {
        this.testingDataLoader = testingDataLoader;
        this.questionProvider = questionProvider;
        this.testingResultHandler = testingResultHandler;
        this.ioService = ioService;
    }

    @Override
    public void initializeTest() {
        this.testingDataLoader.load(this.testingData);
    }

    @Override
    public void initializeUser() {
        ioService.outputString("Enter name: ");

        this.user = new User(ioService.readString());

        ioService.outputString(String.format("Hello, %s", user.getName()));
        ioService.outputString(getHelp());
    }

    @Override
    public void runTest() {
        ioService.outputString("the test has started...");

        for (Map.Entry<Question, Integer> questionEntry : this.testingData.entrySet()) {

            String inputAnswer = askQuestionAndReturnAnswer(questionEntry.getKey());

            if (inputAnswer.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }

            saveInputAnswer(questionEntry.getKey(), Integer.parseInt(inputAnswer));
        }
    }

    @Override
    public void processResult() {
        ioService.outputString(testingResultHandler.getResult(this.user, this.testingData));
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

    private void saveInputAnswer(Question question, int answer) {
        this.testingData.put(question, answer);
    }

    private String getHelp() {
        return "Instructions: \n"
                + "     To complete the test early, enter 'q'\n"
                + "     To call the instructions, enter 'h'\n"
                + "     To answer the question, enter the number of answer";
    }
}
