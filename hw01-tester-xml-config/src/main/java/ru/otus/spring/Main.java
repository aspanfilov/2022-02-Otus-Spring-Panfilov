package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.*;

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

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        ApplicationRunner application = context.getBean(ApplicationRunner.class);
        application.run();
    }

}
