package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.*;

@ComponentScan
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        var testProcessor = context.getBean(TestProcessor.class);
        testProcessor.start();

    }
}
