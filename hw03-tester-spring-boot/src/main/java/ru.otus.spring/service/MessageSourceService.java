package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;

public interface MessageSourceService {

    @Nullable
    String getMessage(String code, @Nullable Object[] args);

}
