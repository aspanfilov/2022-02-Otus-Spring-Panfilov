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

        this.ioService.outputLocaledString("user.enterName");

        User user = new User(this.ioService.readString());

        this.ioService.outputLocaledStringWithArgs(
                "user.hello",
                new String[] {user.getName()});

        return user;
    }
}
