package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.Map;

public interface TestingDataLoader {

    void load(Map<Question, Integer> testingData);

}
