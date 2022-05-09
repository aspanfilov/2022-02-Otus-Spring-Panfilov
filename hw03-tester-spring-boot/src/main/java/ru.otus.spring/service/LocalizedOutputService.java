package ru.otus.spring.service;

public interface LocalizedOutputService {

    void outputMessage(String code);

    void outputMessageWithArgs(String code, Object... args);

}
