package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionSourceException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class QuestionDaoCsv implements QuestionDao {

    private final String questionSourceFileName;

    @Autowired
    public QuestionDaoCsv(@Value("${question.source}") String questionSourceFileName) {
        this.questionSourceFileName = questionSourceFileName;
    }

    @Override
    public List<Question> getAll() {

        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(this.questionSourceFileName)) {

            if (inputStream == null) {
                throw new QuestionSourceException("File not found: " + this.questionSourceFileName);
            }

            try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {

                String[] questionParams;
                while ((questionParams = reader.readNext()) != null) {
                    questions.add(createQuestion(questionParams));
                }
            }

        } catch (CsvValidationException | IOException e) {
            throw new QuestionSourceException(e);
        }

        return questions;
    }

    private Question createQuestion(String[] params) {

        List<Answer> answers = new ArrayList<>();

        IntStream.range(1, params.length - 1).forEach(i ->
                answers.add(new Answer(params[i], i == Integer.parseInt(params[params.length - 1]))));

        return new Question(params[0], answers);

    }
}
