package ru.otus.spring.service.io;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.LocalizationProvider;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;
    private final Locale locale;

    public MessageSourceServiceImpl(MessageSource messageSource, LocalizationProvider localizationProvider) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localizationProvider.getLanguageTag());
    }

    public String getMessage(String code, Object... args) {
        return this.messageSource.getMessage(code, args, this.locale);
    }

}
