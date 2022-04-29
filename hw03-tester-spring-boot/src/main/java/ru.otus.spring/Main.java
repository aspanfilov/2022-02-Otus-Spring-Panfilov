package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import ru.otus.spring.service.IOServiceStreams;
import ru.otus.spring.service.MessageSourceServiceImpl;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        var context = SpringApplication.run(Main.class, args);

        var msg = context.getBean(MessageSource.class);

        var lmsg = context.getBean(MessageSourceServiceImpl.class);

//        System.out.println(msg.getMessage(
//                "strings.EnterName",
//                null,
//                Locale.forLanguageTag("ru-RU")
//        ));

        System.out.println(msg);
        System.out.println(lmsg);

        var io = context.getBean(IOServiceStreams.class);
        System.out.println(io.getInput());
        System.out.println(io.getOutput());
        System.out.println(io.getMessageSourceService());

//        io.outputStringLocaled("strings.EnterName", null);



//        var testProcessor = context.getBean(TestProcessor.class);
//        testProcessor.start();

    }
}
