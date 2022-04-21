package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TestResult {

    private final Map<Question, Optional<Answer>> data = new HashMap<>();

    public TestResult(List<Question> questions) {
        questions.forEach(question -> this.data.put(question, Optional.empty()));
    }

    public void putAnswer(Question question, Answer answer) {
        this.data.put(question, Optional.of(answer));
    }

    public int getNumberOfCorrectAnswers() {
        AtomicInteger numberOfCorrectAnswers = new AtomicInteger(0);
        for (Map.Entry<Question, Optional<Answer>> dataEntry : data.entrySet()) {
            dataEntry.getValue().ifPresent(answer -> {
                if (answer.isRight()) {
                    numberOfCorrectAnswers.getAndIncrement();
                }
            });
        }
        return numberOfCorrectAnswers.get();
    }

    public int getNumberOfQuestions() {
        return data.size();
    }

}
