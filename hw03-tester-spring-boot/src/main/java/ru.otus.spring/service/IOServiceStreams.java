package ru.otus.spring.service;

import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService {

    private final Scanner input;
    private final PrintStream output;
    private final MessageSourceService messageSourceService;

    public IOServiceStreams(
            InputStream inputStream,
            PrintStream outputStream,
            MessageSourceService messageSourceService) {
        this.input = new Scanner(inputStream);
        this.output = outputStream;
        this.messageSourceService = messageSourceService;
    }

    public PrintStream getOutput() {
        return this.output;
    }

    public Scanner getInput() {
        return this.input;
    }

    public MessageSourceService getMessageSourceService() {
        return this.messageSourceService;
    }

    @Override
    public void outputString(String s) {
        this.output.println(s);
    }

    @Override
    public void outputStringLocaled(String s, @Nullable Object[] args) {
        String localedMessage = this.messageSourceService.getMessage(s, args);
        outputString(localedMessage);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(this.input.nextLine());
    }

    @Override
    public String readString() {
        return this.input.nextLine();
    }

}
