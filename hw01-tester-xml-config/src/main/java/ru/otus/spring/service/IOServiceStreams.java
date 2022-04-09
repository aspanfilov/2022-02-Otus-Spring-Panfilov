package ru.otus.spring.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService{

    private PrintStream output;
    private Scanner input;

    public IOServiceStreams() {
    }

    public IOServiceStreams(InputStream inputStream, PrintStream outputStream) {
        this.input = new Scanner(inputStream);
        this.output = outputStream;
    }

    public PrintStream getOutput() {
        return output;
    }

    public void setOutput(PrintStream output) {
        this.output = output;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(InputStream inputStream) {
        this.input = new Scanner(inputStream);
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
