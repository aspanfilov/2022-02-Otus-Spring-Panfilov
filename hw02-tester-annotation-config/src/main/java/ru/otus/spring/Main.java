package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.*;

@ComponentScan
public class Main {

    public static void main(String[] args) {

//        QuestionDao questionDao = new QuestionDaoCsv("/questions.csv");
//        TestingDataLoader testingDataLoader = new TestingDataLoaderImpl(questionDao);
//        QuestionProvider questionProvider = new QuestionProviderImpl();
//        TestingResultHandler testingResultHandler = new TestingResultHandlerImpl();
//        IOService ioService = new IOServiceStreams(System.in, System.out);
//
//        TestingProcessor testProcessor = new TestingProcessorImpl(
//                testingDataLoader,
//                questionProvider,
//                testingResultHandler,
//                ioService);
//
//        var applicationRunner = new ApplicationRunner(testProcessor);

//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("/spring-context.xml");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

//        ApplicationRunner application = context.getBean(ApplicationRunner.class);
//        application.run();


    }

}
