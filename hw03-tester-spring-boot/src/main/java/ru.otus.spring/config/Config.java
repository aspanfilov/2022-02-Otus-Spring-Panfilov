package ru.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceStreams;
import ru.otus.spring.service.TestResultService;
import ru.otus.spring.service.TestResultServiceImpl;

@Configuration
@EnableConfigurationProperties({QuestionDaoCsv.class, TestResultServiceImpl.class})
public class Config {

    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.in, System.out);
    }

    private QuestionDao questionDao;

    private TestResultService testResultService;

}
