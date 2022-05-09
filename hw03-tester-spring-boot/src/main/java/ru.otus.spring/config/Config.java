package ru.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.*;

@Configuration
@EnableConfigurationProperties({
        LocalizatioinConfig.class,
        QuestionSourceConfig.class,
        TestResultConfig.class})
public class Config {

    @Bean
    IOServiceStreams ioService() {
        return new IOServiceStreams(System.in, System.out);
    }

}
