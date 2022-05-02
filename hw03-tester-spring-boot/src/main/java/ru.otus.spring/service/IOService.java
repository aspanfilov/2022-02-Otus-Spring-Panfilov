package ru.otus.spring.service;

import org.springframework.lang.Nullable;

public interface IOService {

    void outputString(String s);

    void outputLocaledString(String s);

    void outputLocaledStringWithArgs(String s, Object[] args);

    int readInt();

    String readString();



}
