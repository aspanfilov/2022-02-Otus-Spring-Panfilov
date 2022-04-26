package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;
import ru.otus.spring.domain.User;

public interface TestResultService {

    void saveAnswer(TestResult testResult, Question question, String inputAnswer);

    String getPassingMessage(TestResult testResult);

    String getResultMessage(TestResult testResult, User user);
}
