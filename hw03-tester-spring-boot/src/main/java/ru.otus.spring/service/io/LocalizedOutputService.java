package ru.otus.spring.service.io;

public interface LocalizedOutputService {

    void outputMessage(String code);

    void outputMessageWithArgs(String code, Object... args);

}
