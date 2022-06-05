package ru.otus.spring.service.io;

import org.springframework.stereotype.Service;
import ru.otus.spring.annotations.Loggable;

@Service
public class LocalizedOutputServiceStreams implements LocalizedOutputService {

    private final IOService ioService;
    private final MessageSourceService messageSourceService;

    public LocalizedOutputServiceStreams(IOService ioService, MessageSourceService messageSourceService) {
        this.ioService = ioService;
        this.messageSourceService = messageSourceService;
    }

    @Loggable
    @Override
    public void outputMessage(String code) {
        String localedMessage = this.messageSourceService.getMessage(code);
        this.ioService.outputString(localedMessage);
    }

    @Loggable
    @Override
    public void outputMessageWithArgs(String code, Object... args) {
        String localedMessage = this.messageSourceService.getMessage(code, args);
        this.ioService.outputString(localedMessage);
    }

}
