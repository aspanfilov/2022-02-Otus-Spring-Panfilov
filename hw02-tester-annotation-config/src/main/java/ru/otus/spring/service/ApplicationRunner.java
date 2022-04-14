package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class ApplicationRunner {

    private final TestingProcessor testProcessor;

//    @Autowired
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

