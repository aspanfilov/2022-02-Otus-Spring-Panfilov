package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.Map;

public class TestingDataLoaderImpl implements TestingDataLoader{

    private final QuestionDao questionDao;

    private static final int EMPTY_ANSWER = 0;

    public TestingDataLoaderImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void load(Map<Question, Integer> testingData) {
        testingData.clear();
        this.questionDao.getAll()
                .forEach(question -> testingData.put(question, EMPTY_ANSWER));
    }
}
