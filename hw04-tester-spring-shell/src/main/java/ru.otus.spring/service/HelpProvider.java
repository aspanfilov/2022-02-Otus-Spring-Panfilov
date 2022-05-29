package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.io.MessageSourceService;

@Service
public class HelpProvider {

    private final static String INDENT = "     ";

    private final MessageSourceService messageSourceService;

    public HelpProvider(MessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    public String getInstruction() {

        String sb = messageSourceService.getMessage("instruction.title") +
                "\n" + INDENT +
                messageSourceService.getMessage("instruction.escape") +
                "\n" + INDENT +
                messageSourceService.getMessage("instruction.instruction") +
                "\n" + INDENT +
                messageSourceService.getMessage("instruction.answer");

        return sb;
    }

}
