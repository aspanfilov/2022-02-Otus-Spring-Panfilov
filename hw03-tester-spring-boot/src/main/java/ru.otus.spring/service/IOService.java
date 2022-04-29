package ru.otus.spring.service;

import org.springframework.lang.Nullable;

public interface IOService {

    void outputString(String s);

    void outputStringLocaled(String s, @Nullable Object[] args);

    int readInt();

    String readString();



}
