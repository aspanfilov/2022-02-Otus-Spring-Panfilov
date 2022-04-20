package ru.otus.spring.exception;

public class QuestionSourceException extends RuntimeException{

    public QuestionSourceException(Exception e) {
        super(e);
    }

    public QuestionSourceException(String message) {
        super(message);
    }
}
