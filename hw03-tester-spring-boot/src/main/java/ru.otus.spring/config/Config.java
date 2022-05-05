package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.*;

//@ConfigurationProperties(prefix = "localization")
@Configuration
@EnableConfigurationProperties({
        QuestionDaoCsv.class,
        TestResultServiceImpl.class,
        MessageSourceServiceImpl.class})
public class Config {

    private QuestionDao questionDao;

    private TestResultService testResultService;

    public MessageSourceService messageSourceService;

    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.in, System.out, messageSourceService);
    }

}
