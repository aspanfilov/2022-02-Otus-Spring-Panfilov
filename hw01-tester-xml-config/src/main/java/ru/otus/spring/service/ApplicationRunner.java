package ru.otus.spring.service;

public class ApplicationRunner {

    private final TestingProcessor testProcessor;

    public ApplicationRunner(TestingProcessor testProcessor){
        this.testProcessor = testProcessor;
    }

    public void run() {

        testProcessor.initializeTest();
        testProcessor.initializeUser();
        testProcessor.runTest();
        testProcessor.processResult();

    }



}

