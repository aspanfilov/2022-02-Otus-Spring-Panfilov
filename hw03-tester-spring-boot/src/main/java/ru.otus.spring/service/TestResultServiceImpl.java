package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;


@ConfigurationProperties(prefix = "test-result")
@ConstructorBinding
public class TestResultServiceImpl implements TestResultService{

    @Autowired
    private final MessageSourceService messageSourceService;
    private final int passingPercentage;

    public TestResultServiceImpl(
            MessageSourceService messageSourceService,
            int passingPercentage) {
        this.messageSourceService = messageSourceService;
        this.passingPercentage = passingPercentage;
    }

    @Override
    public void saveAnswer(TestResult testResult, Question question, String inputAnswer) {
        int inputAnswerId = Integer.parseInt(inputAnswer) - 1;
        testResult.putAnswer(
                question,
                question.getAnswer(inputAnswerId));
    }

    @Override
    public String getPassingMessage(TestResult testResult) {
        int percent = testResult.getNumberOfCorrectAnswers() * 100 / testResult.getNumberOfQuestions();
        if (percent >= this.passingPercentage) {
            return messageSourceService.getMessage("result.passed", null);
        } else {
            return messageSourceService.getMessage("result.missed", null);
        }
    }

    @Override
    public String getResultMessage(TestResult testResult, User user) {
        String msg = this.messageSourceService.getMessage(
                "result.message",
                new String[] {
                        user.getName(),
                        Integer.valueOf(testResult.getNumberOfCorrectAnswers()).toString(),
                        Integer.valueOf(testResult.getNumberOfQuestions()).toString()})
                + "\n" + getPassingMessage(testResult);
        return msg;
    }


}
