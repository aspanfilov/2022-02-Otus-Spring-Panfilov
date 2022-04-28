package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

@Service
public class UserServiceImpl implements UserService{

    private final IOService ioService;

    public UserServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public User getUser() {

//        this.ioService.outputString("Enter name: ");
        this.ioService.outputString("Enter name: ");

        User user = new User(this.ioService.readString());

        this.ioService.outputString(String.format("Hello, %s", user.getName()));

        return user;
    }
}
