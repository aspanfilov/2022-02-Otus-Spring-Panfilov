package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class HelpProvider {

    private final static String INDENT = "     ";

    private final MessageSourceService messageSourceService;

    public HelpProvider(MessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    public String getInstruction() {

        StringBuilder sb = new StringBuilder();
        sb.append(messageSourceService.getMessage("instruction.title", null));
        sb.append("\n").append(INDENT);
        sb.append(messageSourceService.getMessage("instruction.escape", null));
        sb.append("\n").append(INDENT);
        sb.append(messageSourceService.getMessage("instruction.instruction", null));
        sb.append("\n").append(INDENT);
        sb.append(messageSourceService.getMessage("instruction.answer", null));

        return sb.toString();
    }

}
