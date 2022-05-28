package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("Класс Question")
class QuestionTest {

    @Test
    @DisplayName("корректно создается конструктором")
    void shouldHaveCorrectConstructor() {
        String questionPhrase = "What is the capital of Great Britain?";
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(questionPhrase, answers);

        assertThat(question.getQuestionPhrase()).isEqualTo(questionPhrase);
        assertThat(question.getAnswers()).isEqualTo(answers);
    }

    @Test
    @DisplayName("метод getAnswer возвращает запрашиваемый ответ")
    void methodGetAnswer_shouldReturnRequestedAnswer() {
        Answer answerMock1 = mock(Answer.class);
        Answer answerMock2 = mock(Answer.class);
        List<Answer> answers = List.of(answerMock1, answerMock2);

        Question question = new Question("question?", answers);

        assertThat(question.getAnswer(0)).isEqualTo(answerMock1);
        assertThat(question.getAnswer(1)).isEqualTo(answerMock2);
    }

}