package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.LocalizatioinConfig;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl implements MessageSourceService{

    private final MessageSource messageSource;
    private final Locale locale;

    public MessageSourceServiceImpl(MessageSource messageSource, LocalizatioinConfig localizatioinConfig) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localizatioinConfig.getLanguageTag());
    }

    public String getMessage(String code, Object... args) {
        return this.messageSource.getMessage(code, args, this.locale);
    }

}
