package ru.otus.spring.domain;

import java.util.List;

public class Question {
    private final String questionPhrase;
    private final List<Answer> answers;

    public Question(String questionPhrase, List<Answer> answers) {
        this.questionPhrase = questionPhrase;
        this.answers = answers;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAnswer(int index) {
        return answers.get(index);
    }
}
