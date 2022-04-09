package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class QuestionProviderImpl implements QuestionProvider {

    private final List<String> availableInputAnswers = new ArrayList<>();

    @Override
    public String getQuestionPhrase(Question question) {
        return question.getQuestionPhrase();
    }

    @Override
    public String getAnswerVariants(Question question) {
        StringBuilder sb = new StringBuilder();
        List<String> answers = question.getAnswers();

        for (int index = 0; index < answers.size(); index++) {
            sb.append(String.format("%s.%s ", index + 1, answers.get(index)));
        }
        return sb.toString();
    }

    @Override
    public List<String> getAvailableInputAnswers(Question question) {
        if (this.availableInputAnswers.size() != (question.getAnswers().size())) {
            updateAvailableInputAnswers(question);
        }
        return this.availableInputAnswers;
    }

    private void updateAvailableInputAnswers(Question question) {
        this.availableInputAnswers.clear();
        IntStream.range(1, question.getAnswers().size()+1)
                .mapToObj(Integer::toString)
                .forEach(this.availableInputAnswers::add);
    }
}
