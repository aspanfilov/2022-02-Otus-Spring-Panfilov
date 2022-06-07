package ru.otus.spring.exception;

public class QuestionCreationException extends RuntimeException{

    public QuestionCreationException(Exception e) {
        super(e);
    }

    public QuestionCreationException(String message) {
        super(message);
    }
}
