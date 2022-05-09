package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "test-result")
@ConstructorBinding
public class TestResultConfig {

    private final int passingPercentage;

    public TestResultConfig(int passingPercentage) {
        this.passingPercentage = passingPercentage;
    }

    public int getPassingPercentage() {
        return passingPercentage;
    }

}
