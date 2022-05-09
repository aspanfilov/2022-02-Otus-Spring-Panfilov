package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.TestResultConfig;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

@Service
public class TestResultServiceImpl implements TestResultService {

    private final MessageSourceService messageSourceService;
    private final int passingPercentage;

    public TestResultServiceImpl(
            MessageSourceService messageSourceService,
            TestResultConfig testResultConfig) {
        this.messageSourceService = messageSourceService;
        this.passingPercentage = testResultConfig.getPassingPercentage();
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
            return messageSourceService.getMessage("result.passed");
        } else {
            return messageSourceService.getMessage("result.missed");
        }
    }

    @Override
    public String getResultMessage(TestResult testResult, User user) {
        String msg = this.messageSourceService.getMessage(
                "result.message",
                user.getName(),
                Integer.valueOf(testResult.getNumberOfCorrectAnswers()).toString(),
                Integer.valueOf(testResult.getNumberOfQuestions()).toString())
                + "\n" + getPassingMessage(testResult);
        return msg;
    }


}
