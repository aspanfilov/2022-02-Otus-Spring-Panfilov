package ru.otus.spring.domain;

import java.util.List;

public class Question {
    private final String questionPhrase;
    private final List<String>answers;
    private final int correctIndex;

    public Question(String questionPhrase, List<String> answers, int correctIndex) {
        this.questionPhrase = questionPhrase;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public Answer getAnswer(int index) {

    }
}
