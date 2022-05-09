package ru.otus.spring.service;

public interface MessageSourceService {

    String getMessage(String code, Object... args);
}
