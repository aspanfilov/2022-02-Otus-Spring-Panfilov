package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс QuestionTest")
class QuestionTest {

    @Test
    @DisplayName("корректно создается конструктором")
    void shouldHaveCorrectConstructor() {
        String questionPhrase = "What is the capital of Great Britain?";
        List<String> answers = List.of("London", "Moscow", "Washington", "Berlin", "Paris");
        int correctIndex = 1;

        Question question = new Question(questionPhrase, answers, correctIndex);

        assertThat(question.getQuestionPhrase()).isEqualTo(questionPhrase);
        assertThat(question.getAnswers()).isEqualTo(answers);
        assertThat(question.getCorrectIndex()).isEqualTo(correctIndex);
    }

}