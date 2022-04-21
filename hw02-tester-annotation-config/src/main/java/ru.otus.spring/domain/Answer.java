package ru.otus.spring.domain;

public class Answer {
    private final String answerPhrase;
    private final boolean isRight;

    public Answer(String answerPhrase, boolean isRight) {
        this.answerPhrase = answerPhrase;
        this.isRight = isRight;
    }

    public String getAnswerPhrase() {
        return answerPhrase;
    }

    public boolean isRight() {
        return isRight;
    }
}
