package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoCsv implements QuestionDao {

    private final String questionSourceFileName;

    public QuestionDaoCsv(String questionSourceFileName) {
        this.questionSourceFileName = questionSourceFileName;
    }

    @Override
    public List<Question> getAll() {

        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(this.questionSourceFileName)) {

            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + this.questionSourceFileName);
            }

            try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {

                String[] questionParams;
                while ((questionParams = reader.readNext()) != null) {
                    questions.add(createQuestion(questionParams));
                }
            }

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private Question createQuestion(String[] params) {

        List<String> answers = Arrays.stream(params, 1, params.length - 1)
                .collect(Collectors.toList());

        return new Question(
                params[0],
                answers,
                Integer.parseInt(params[params.length - 1]));
    }
}
