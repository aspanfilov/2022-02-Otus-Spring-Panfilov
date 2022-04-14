package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.Map;

public interface TestingResultHandler {

    String getResult(User user, Map<Question, Integer> testingData);

}
