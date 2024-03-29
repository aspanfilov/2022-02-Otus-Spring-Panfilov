package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceStreams;

@PropertySource("classpath:application.properties")
@Configuration
public class Config {

    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.in, System.out);
    }

}
