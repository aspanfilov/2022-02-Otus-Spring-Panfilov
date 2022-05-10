package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "settings")
@ConstructorBinding
public class AppSettings implements LocalizationProvider, QuestionSourceProvider, TestResultSettingsProvider{

    private final String languageTag;
    private final String questionFileName;
    private final int testPassingPercentage;

    public AppSettings(String languageTag, String questionFileName, int testPassingPercentage) {
        this.languageTag = languageTag;
        this.questionFileName = questionFileName;
        this.testPassingPercentage = testPassingPercentage;
    }

    @Override
    public String getLanguageTag() {
        return this.languageTag;
    }

    @Override
    public String getQuestionFileName() {
        String localizedQuestionFileName;
        if (this.languageTag.isEmpty() || this.languageTag.equals("en-EN")) {
            localizedQuestionFileName = this.questionFileName;
        } else {
            localizedQuestionFileName = this.questionFileName.substring(0, this.questionFileName.length() - 4)
                    + "_" + this.languageTag.substring(this.languageTag.length()-2)
                    + this.questionFileName.substring(this.questionFileName.length() - 4);
        }
        return localizedQuestionFileName;
    }

    @Override
    public int getPassingPercentage() {
        return this.testPassingPercentage;
    }

}
