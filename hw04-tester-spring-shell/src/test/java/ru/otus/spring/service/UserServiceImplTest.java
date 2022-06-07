package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.service.io.IOService;
import ru.otus.spring.service.io.LocalizedOutputService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс UserServiceImpl")
class UserServiceImplTest {

    @DisplayName("метод introduceAndReturnUser возвращает нового пользователя")
    @Test
    void introduceAndReturnUser_shouldReturnNewUser() {
        var ioService = mock(IOService.class);
        when(ioService.readString()).thenReturn("Ivan");
        var localizedOutputService = mock(LocalizedOutputService.class);

        var userServiceImpl = new UserServiceImpl(ioService, localizedOutputService);
        var user = userServiceImpl.introduceAndReturnUser();
        assertThat(user.getName()).isEqualTo("Ivan");
    }
}