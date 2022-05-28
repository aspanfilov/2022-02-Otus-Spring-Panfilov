package ru.otus.spring.service.io;

public interface MessageSourceService {

    String getMessage(String code, Object... args);
}
