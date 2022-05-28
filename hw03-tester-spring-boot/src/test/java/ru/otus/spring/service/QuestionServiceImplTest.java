package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionServiceImpl")
class QuestionServiceImplTest {

    @DisplayName("метод getAll возвращает список вопросов")
    @Test
    void methodGetAll_shouldReturnListOfQuestions() {
        List<Question> questions = new ArrayList<>();

        QuestionDao questionDaoMock = mock(QuestionDao.class);
        when(questionDaoMock.getAll()).thenReturn(questions);

        QuestionService questionService = new QuestionServiceImpl(questionDaoMock);
        assertThat(questionService.getAll()).isEqualTo(questions);
    }


}