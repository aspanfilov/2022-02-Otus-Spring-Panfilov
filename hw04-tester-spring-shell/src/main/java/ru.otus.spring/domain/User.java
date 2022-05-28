package ru.otus.spring.domain;

public class User {

    private final String name;

    public User(String name) {
        this.name = name;
        System.out.println();
    }

    public String getName() {
        return name;
    }
}
