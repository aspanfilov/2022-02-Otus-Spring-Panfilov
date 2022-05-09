package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class LocalizedOutputServiceStreams implements LocalizedOutputService{

    private final IOService ioService;
    private final MessageSourceService messageSourceService;

    public LocalizedOutputServiceStreams(IOService ioService, MessageSourceService messageSourceService) {
        this.ioService = ioService;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public void outputMessage(String code) {
        String localedMessage = this.messageSourceService.getMessage(code);
        this.ioService.outputString(localedMessage);
    }

    @Override
    public void outputMessageWithArgs(String code, Object... args) {
        String localedMessage = this.messageSourceService.getMessage(code, args);
        this.ioService.outputString(localedMessage);
    }

}
