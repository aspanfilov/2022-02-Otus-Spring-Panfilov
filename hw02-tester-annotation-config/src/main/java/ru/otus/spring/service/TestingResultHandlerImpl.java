package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.Map;

@Service
public class TestingResultHandlerImpl implements TestingResultHandler {

    @Override
    public String getResult(User user, Map<Question, Integer> testingData) {
        return user.getName()
                + ", you have answered " + getNumberOfCorrect(testingData)
                + " out of " + getNumberOfTotal(testingData)
                + " questions";
    }

    private int getNumberOfCorrect(Map<Question, Integer> testingData) {
        int numberOfCorrect = 0;
        for(Map.Entry<Question, Integer> questionEntry : testingData.entrySet()) {
            if (questionEntry.getKey().getCorrectIndex() == questionEntry.getValue()) {
                numberOfCorrect++;
            }
        }
        return numberOfCorrect;
    }

    private int getNumberOfTotal(Map<Question, Integer> testingData) {
        return testingData.size();
    }

}
