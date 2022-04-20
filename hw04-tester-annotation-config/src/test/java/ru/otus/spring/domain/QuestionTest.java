package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        //todo сделать тест с двумя моками ответов
    }

}