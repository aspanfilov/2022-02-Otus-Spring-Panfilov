package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class QuestionInterpretaterImpl implements QuestionInterpretater {

    @Override
    public String getQuestionPhrase(Question question) {
        return question.getQuestionPhrase();
    }

    @Override
    public String getAnswerVariants(Question question) {
        StringBuilder sb = new StringBuilder();
        List<Answer> answers = question.getAnswers();

        for (int index = 0; index < answers.size(); index++) {
            sb.append(String.format("%s.%s ", index + 1, answers.get(index).getAnswerPhrase()));
        }
        return sb.toString();
    }

    @Override
    public List<String> getAvailableInputAnswers(Question question) {

        List<String> availableInputAnswers =
                IntStream.range(1, question.getAnswers().size() + 1)
                .mapToObj(Integer::toString).toList();

        return availableInputAnswers;
    }


}
