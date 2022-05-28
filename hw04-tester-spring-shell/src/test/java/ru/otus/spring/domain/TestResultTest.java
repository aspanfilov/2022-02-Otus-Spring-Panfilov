package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс TestResult")
class TestResultTest {
    private Question questionMock1;
    private Question questionMock2;
    private Question questionMock3;
    private List<Question> questions;

    private TestResult testResult;

    @BeforeEach
    void setUp() {
        this.questionMock1 = mock(Question.class);
        this.questionMock2 = mock(Question.class);
        this.questionMock3 = mock(Question.class);

        this.questions = List.of(questionMock1, questionMock2, questionMock3);

        this.testResult = new TestResult(questions);
    }

    @DisplayName("метод getNumberOfCorrectAnswers возвращает количество правильных ответов")
    @Test
    void getNumberOfCorrectAnswers_shouldReturnNumberOfCorrectAnswers() {
        Answer rightAnswerMock1 = mock(Answer.class);
        when(rightAnswerMock1.isRight()).thenReturn(true);
        this.testResult.putAnswer(this.questionMock1, rightAnswerMock1);

        Answer rightAnswerMock2 = mock(Answer.class);
        when(rightAnswerMock2.isRight()).thenReturn(true);
        this.testResult.putAnswer(this.questionMock2, rightAnswerMock2);

        Answer wrongAnswerMock = mock(Answer.class);
        when(wrongAnswerMock.isRight()).thenReturn(false);
        this.testResult.putAnswer(this.questionMock3, wrongAnswerMock);

        assertThat(this.testResult.getNumberOfCorrectAnswers()).isEqualTo(2);
    }

    @DisplayName("метод getNumberOfCorrectAnswers возвращает количество правильных ответов, если ответов меньше вопросов")
    @Test
    void getNumberOfCorrectAnswers_shouldReturnNumberOfCorrectAnswersWhenAnswersLessThanQuestions() {
        Answer rightAnswerMock = mock(Answer.class);
        when(rightAnswerMock.isRight()).thenReturn(true);
        this.testResult.putAnswer(this.questionMock1, rightAnswerMock);

        Answer wrongAnswerMock = mock(Answer.class);
        when(wrongAnswerMock.isRight()).thenReturn(false);
        this.testResult.putAnswer(this.questionMock2, wrongAnswerMock);

        assertThat(this.testResult.getNumberOfCorrectAnswers()).isEqualTo(1);
    }

    @DisplayName("метод getNumberOfCorrectAnswers возвращает количество правильных ответов, если ответов не было")
    @Test
    void getNumberOfCorrectAnswers_shouldReturnNumberOfCorrectAnswersWhenNoAnswers() {
        assertThat(this.testResult.getNumberOfCorrectAnswers()).isEqualTo(0);
    }


    @DisplayName("метод getNumberOfQuestions возвращает количество вопросов")
    @Test
    void getNumberOfQuestions_shouldReturnNumberOfQuestions() {
        assertThat(this.testResult.getNumberOfQuestions()).isEqualTo(3);
    }
}