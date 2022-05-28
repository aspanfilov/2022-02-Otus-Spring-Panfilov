package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuestionInterpretaterImpl")
class QuestionInterpretaterImplTest {

    private Answer answerMock1;
    private Answer answerMock2;
    private Question questionMock;
    private QuestionInterpretater questionProvider;

    @BeforeEach
    void setUp() {
        answerMock1 = mock(Answer.class);
        when(answerMock1.getAnswerPhrase()).thenReturn("answer1");
        answerMock2 = mock(Answer.class);
        when(answerMock2.getAnswerPhrase()).thenReturn("answer2");
        List<Answer> answers = List.of(answerMock1, answerMock2);

        questionMock = mock(Question.class);
        when(questionMock.getAnswers()).thenReturn(answers);

        questionProvider = new QuestionInterpretaterImpl();
    }

    @DisplayName("Метод getQuestionPhrase возвращает вопрос")
    @Test
    void getQuestionPhrase_shouldReturnQuestionPhrase() {

        String questionPhase = "question";
        when(questionMock.getQuestionPhrase()).thenReturn(questionPhase);

        assertThat(questionProvider.getQuestionPhrase(questionMock))
                .isEqualTo(questionPhase);
    }

    @DisplayName("Метод getAnswerVariants возвращает варианты ответов")
    @Test
    void getAnswerVariants_shouldReturnAnswerVariants() {

        assertThat(questionProvider.getAnswerVariants(questionMock))
                .isEqualTo("1.answer1 2.answer2 ");
    }

    @DisplayName("Метод getAvailableInputAnswers возвращает доступные ответы для ввода")
    @Test
    void getAvailableInputAnswers_shouldReturnAvailableInputAnswers() {

        List<String> availableInputAnswers = List.of("1","2");

        assertThat(questionProvider.getAvailableInputAnswers(questionMock))
                .containsExactlyElementsOf(availableInputAnswers);

    }
}