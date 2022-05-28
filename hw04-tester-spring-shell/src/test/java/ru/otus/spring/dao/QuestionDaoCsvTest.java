package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.config.QuestionSourceProvider;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionSourceException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionDaoCsv")
class QuestionDaoCsvTest {

    private QuestionSourceProvider questionSourceProvider;

    @BeforeEach
    void setUp() {
        this.questionSourceProvider = mock(QuestionSourceProvider.class);
        when(this.questionSourceProvider.getQuestionFileName()).thenReturn("/testQuestions.csv");
    }

    @DisplayName("метод getAll возвращает список вопросов")
    @Test
    void methodGetAll_shouldReturnListOfQuestions() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv(this.questionSourceProvider);
        List<Question> questions = questionDaoCsv.getAll();
        assertThat(questions)
                .isInstanceOf(ArrayList.class)
                .hasSize(3);
    }

    @DisplayName("метод getAll возвращает корректно сформированный вопрос")
    @Test
    void methodGetAll_shouldReturnCorrectCreatedQuestions() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv(this.questionSourceProvider);
        List<Question> questions = questionDaoCsv.getAll();

        assertThat(questions.get(0).getQuestionPhrase())
                .isEqualTo("What is the capital of Great Britain?");

        assertThat(questions.get(0).getAnswers().get(0).getAnswerPhrase())
                .isEqualTo("London");
        assertThat(questions.get(0).getAnswers().get(0).isRight())
                .isEqualTo(true);

        assertThat(questions.get(0).getAnswers().get(1).getAnswerPhrase())
                .isEqualTo("Moscow");
        assertThat(questions.get(0).getAnswers().get(1).isRight())
                .isEqualTo(false);

        assertThat(questions.get(0).getAnswers().get(2).getAnswerPhrase())
                .isEqualTo("Washington");
        assertThat(questions.get(0).getAnswers().get(2).isRight())
                .isEqualTo(false);

        assertThat(questions.get(0).getAnswers().get(3).getAnswerPhrase())
                .isEqualTo("Berlin");
        assertThat(questions.get(0).getAnswers().get(3).isRight())
                .isEqualTo(false);

        assertThat(questions.get(0).getAnswers().get(4).getAnswerPhrase())
                .isEqualTo("Paris");
        assertThat(questions.get(0).getAnswers().get(4).isRight())
                .isEqualTo(false);

    }

    @DisplayName("метод getAll кинет ожидаемое исключение, если объект создан по неверному ресурсу")
    @Test
    void methodGetAll_shouldThrowExpectedExceptionWhenConstructorWithWrongResource() {
        when(this.questionSourceProvider.getQuestionFileName()).thenReturn("/wrong_name.csv");
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv(this.questionSourceProvider);
        assertThatExceptionOfType(QuestionSourceException.class)
                .isThrownBy(questionDaoCsv::getAll);
    }

}