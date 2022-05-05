package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@ConfigurationProperties(prefix = "localization")
//@ConstructorBinding
@Component
public class MessageSourceServiceImpl implements MessageSourceService{

    private final MessageSource messageSource;

    public MessageSource getMessageSource() {
        return this.messageSource;
    }

    private final String LanguageTag;

    private Locale locale;

    public MessageSourceServiceImpl(MessageSource messageSource, String LanguageTag) {
        this.messageSource = messageSource;
        this.LanguageTag = LanguageTag;
        this.locale = Locale.forLanguageTag(LanguageTag);
    }

    @Nullable
    public String getMessage(String code, @Nullable Object[] args) {
        return this.messageSource.getMessage(code, args, this.locale);
    }

}
