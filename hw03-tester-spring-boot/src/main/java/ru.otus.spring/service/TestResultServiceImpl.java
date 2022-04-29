package ru.otus.spring.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;


@ConfigurationProperties(prefix = "test-result")
@ConstructorBinding
public class TestResultServiceImpl implements TestResultService{

    private final int passingPercentage;

    private static final String PASSED = "PASSED";
    private static final String MISSED = "MISSED";

    public TestResultServiceImpl(int passingPercentage) {
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
            return PASSED;
        } else {
            return MISSED;
        }
    }

    @Override
    public String getResultMessage(TestResult testResult, User user) {
        String msg = user.getName()
                + ", you have answered " + testResult.getNumberOfCorrectAnswers()
                + " out of " + testResult.getNumberOfQuestions()
                + " questions \n"
                + getPassingMessage(testResult);
        return msg;
    }


}
