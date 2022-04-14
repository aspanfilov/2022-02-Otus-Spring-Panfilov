package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.Map;

@Service
public class TestingDataLoaderImpl implements TestingDataLoader{

    private final QuestionDao questionDao;

    private static final int EMPTY_ANSWER = 0;

    @Autowired
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
