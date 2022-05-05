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

//        var ioservice = context.getBean(IOServiceStreams.class);
        var msgSource = context.getBean(MessageSource.class);
        System.out.println("msgSource = " + msgSource);

        var msgSourceService = context.getBean(MessageSourceService.class);
        System.out.println("msgSourceService = " + msgSourceService);
        System.out.println("msgSourceService.messageSource = " + msgSourceService.getMessageSource());



//        var testProcessor = context.getBean(TestProcessor.class);
//        testProcessor.start();

    }
}
