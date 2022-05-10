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

@DisplayName("Класс QuestionProviderImpl")
class QuestionInterpretaterImplTest {

    private Answer answerMock1;
    private Answer answerMock2;
    private Question questionMock;
    private QuestionInterpretater questionProvider;


    @BeforeEach
    void setUp() {
        this.answerMock1 = mock(Answer.class);
        when(this.answerMock1.getAnswerPhrase()).thenReturn("answer1");
        this.answerMock2 = mock(Answer.class);
        when(this.answerMock2.getAnswerPhrase()).thenReturn("answer2");
        List<Answer> answers = List.of(answerMock1, answerMock2);

        this.questionMock = mock(Question.class);
        when(questionMock.getAnswers()).thenReturn(answers);

        this.questionProvider = new QuestionInterpretaterImpl();
    }

    @DisplayName("Метод getQuestionPhrase возвращает вопрос")
    @Test
    void getQuestionPhrase_shouldReturnQuestionPhrase() {

        String questionPhase = "question";
        when(this.questionMock.getQuestionPhrase()).thenReturn(questionPhase);

        assertThat(this.questionProvider.getQuestionPhrase(this.questionMock))
                .isEqualTo(questionPhase);
    }

    @DisplayName("Метод getAnswerVariants возвращает варианты ответов")
    @Test
    void getAnswerVariants_shouldReturnAnswerVariants() {

        assertThat(this.questionProvider.getAnswerVariants(this.questionMock))
                .isEqualTo("1.answer1 2.answer2 ");
    }

    @DisplayName("Метод getAvailableInputAnswers возвращает доступные ответы для ввода")
    @Test
    void getAvailableInputAnswers_shouldReturnAvailableInputAnswers() {

        List<String> availableInputAnswers = List.of("1","2");

        assertThat(this.questionProvider.getAvailableInputAnswers(this.questionMock))
                .containsExactlyElementsOf(availableInputAnswers);

    }
}