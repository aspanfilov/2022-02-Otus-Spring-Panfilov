package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public interface QuestionProvider {

    String getQuestionPhrase(Question question);

    String getAnswerVariants(Question question);

    List<String> getAvailableInputAnswers(Question question);
}
