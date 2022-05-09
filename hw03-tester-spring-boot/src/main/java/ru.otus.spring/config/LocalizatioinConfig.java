package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "localization")
@ConstructorBinding
public class LocalizatioinConfig {

    private final String languageTag;

    public LocalizatioinConfig(String languageTag) {
        this.languageTag = languageTag;
    }

    public String getLanguageTag() {
        return languageTag;
    }


}
