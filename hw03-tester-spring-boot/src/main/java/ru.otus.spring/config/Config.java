package ru.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.*;

@Configuration
@EnableConfigurationProperties({
        QuestionDaoCsv.class,
        TestResultServiceImpl.class,
        MessageSourceServiceImpl.class})
public class Config {

    private QuestionDao questionDao;

    private TestResultService testResultService;

    private MessageSourceService messageSourceService;

    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.in, System.out, messageSourceService);
    }

}
