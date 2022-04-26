package ru.otus.spring.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService {

    private final Scanner input;
    private final PrintStream output;

    public IOServiceStreams(
            InputStream inputStream,
            PrintStream outputStream) {
        this.input = new Scanner(inputStream);
        this.output = outputStream;
    }

    public PrintStream getOutput() {
        return output;
    }

    public Scanner getInput() {
        return input;
    }

    @Override
    public void outputString(String s) {
        this.output.println(s);
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
