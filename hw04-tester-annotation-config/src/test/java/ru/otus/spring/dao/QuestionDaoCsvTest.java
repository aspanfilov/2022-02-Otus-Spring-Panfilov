package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionSourceException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс QuestionDaoCsv")
class QuestionDaoCsvTest {

    @DisplayName("метод getAll возвращает список всех вопросов")
    @Test
    void methodGetAll_shouldReturnListOfAllQuestions() {

        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/questions.csv");
        List<Question> questions = questionDaoCsv.getAll();
        assertThat(questions)
                .isInstanceOf(ArrayList.class)
                .hasSize(3);
    }

    @DisplayName("метод getAll кинет ожидаемое исключение, если объект создан по неверному ресурсу")
    @Test
    void methodGetAll_shouldThrowExpectedExceptionWhenConstructorWithWrongResource() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/wrong_name.csv");
        assertThatExceptionOfType(QuestionSourceException.class)
                .isThrownBy(questionDaoCsv::getAll);
    }

}