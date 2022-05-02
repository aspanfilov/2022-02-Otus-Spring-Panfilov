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

//        var msg = context.getBean(MessageSource.class);
//        System.out.println(msg);
//        System.out.println(msg.getMessage(
//                "strings.EnterName",
//                null,
//                Locale.forLanguageTag("ru-RU")
//        ));
//
//        var msgService = context.getBean(MessageSourceService.class);
////        var msgService = new MessageSourceServiceImpl(msg, "ru-RU");
//        System.out.println(msgService);
//        System.out.println(msgService.getMessage(
//                "strings.EnterName",
//                null
//        ));
//
//        System.out.println();
//        var io = context.getBean(IOService.class);
//        io.outputStringLocaled("strings.EnterName", null);


        var testProcessor = context.getBean(TestProcessor.class);
        testProcessor.start();

    }
}
