package ru.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.spring.annotations.Loggable;
import ru.otus.spring.service.io.IOServiceStreams;

@EnableAspectJAutoProxy(proxyTargetClass = false)
@Configuration
@EnableConfigurationProperties(AppSettings.class)
public class Config {

    @Bean
    IOServiceStreams ioService() {
        return new IOServiceStreams(System.in, System.out);
    }

}
