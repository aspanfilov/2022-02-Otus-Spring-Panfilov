package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

@Service
public class UserServiceImpl implements UserService{

    private final IOService ioService;
    private final LocalizedOutputService localizedOutputService;

    public UserServiceImpl(IOService ioService, LocalizedOutputService localizedOutputService) {
        this.ioService = ioService;
        this.localizedOutputService = localizedOutputService;
    }

    @Override
    public User getUser() {

        this.localizedOutputService.outputMessage("user.enterName");

        User user = new User(this.ioService.readString());

        this.localizedOutputService.outputMessageWithArgs(
                "user.hello",
                user.getName());

        return user;
    }
}
