package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.TestResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс TestResultService")
@ExtendWith(MockitoExtension.class)
class TestResultServiceImplTest {

    @Mock
    private Question question;
    @Mock
    private TestResult testResult;
    @Mock
    private Answer answer;

    @DisplayName("метод saveAnswer должен корректно сохранять пользовательский ответ")
    @Test
    void saveAnswer_shouldCorrectSaveUserAnswerIntoTestResult() {
        when(question.getAnswer(1)).thenReturn(answer);

        var testResultService = new TestResultServiceImpl(70);
        testResultService.saveAnswer(testResult, question, "2");

        verify(testResult).putAnswer(question, answer);

        var captor = ArgumentCaptor.forClass(Integer.class);
        verify(question).getAnswer(captor.capture());
        var inputAnswerId = captor.getValue();
        assertThat(inputAnswerId).isEqualTo(1);
    }

    @DisplayName("метод getPassingMessage должен корректно вычислять прохождение тестирования")
    @Test
    void getPassingMessage_shouldCorrectCalculatePassingMessage() {
        when(testResult.getNumberOfQuestions()).thenReturn(10);

        when(testResult.getNumberOfCorrectAnswers()).thenReturn(9);

        var testResultService = new TestResultServiceImpl(70);

        assertThat(testResultService.getPassingMessage(testResult))
                .isEqualTo("PASSED");

        when(testResult.getNumberOfCorrectAnswers()).thenReturn(6);

        assertThat(testResultService.getPassingMessage(testResult))
                .isEqualTo("MISSED");
    }

}