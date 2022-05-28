package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Answer")
class AnswerTest {

    @Test
    @DisplayName("корректно создается конструктором")
    void shouldHaveCorrectConstructor() {
        String answerPhrase = "answer";
        boolean isRight = true;

        Answer answer = new Answer(answerPhrase, isRight);

        assertThat(answer.getAnswerPhrase()).isEqualTo(answerPhrase);
        assertThat(answer.isRight()).isEqualTo(isRight);
    }
}