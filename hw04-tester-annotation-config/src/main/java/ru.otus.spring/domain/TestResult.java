package ru.otus.spring.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TestResult {
    private final User user;
    private final Map<Question, Optional<Answer>> data = new HashMap<>();

    public TestResult(User user, List<Question> questions) {
        this.user = user;
        questions.forEach(question -> this.data.put(question, Optional.empty()));
    }

    public void putAnswer(Question question, Answer answer) {
        this.data.put(question, Optional.of(answer));
    }

    public int getNumberOfCorrectAnswers() {
        AtomicInteger numberOfCorrectAnswers = new AtomicInteger();
        for (Map.Entry<Question, Optional<Answer>> dataEntry : data.entrySet()) {
            dataEntry.getValue().ifPresent(answer -> {
                if (answer.isRight()) {
                    numberOfCorrectAnswers.getAndIncrement();
                }
            });
        }
        return numberOfCorrectAnswers.get();
    }
}
