package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import ru.otus.spring.service.*;

import java.util.Locale;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        var context = SpringApplication.run(Main.class, args);

        var testProcessor = context.getBean(TestProcessor.class);
        testProcessor.start();

    }
}
