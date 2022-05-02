package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionCreationException;
import ru.otus.spring.exception.QuestionSourceException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ConfigurationProperties(prefix = "question-source")
@ConstructorBinding
public class QuestionDaoCsv implements QuestionDao {

    private final String fileName;
    private final String LanguageTag;
    private String localedFileName;

    public QuestionDaoCsv(String fileName, String LanguageTag) {
        this.fileName = fileName;
        this.LanguageTag = LanguageTag;
        this.localedFileName = getLocaledFileName();
    }

    private String getLocaledFileName() {
        String result;
        if (this.LanguageTag.isEmpty() || this.LanguageTag.equals("en")) {
            result = this.fileName;
        } else {
            result = this.fileName.substring(0, this.fileName.length() - 4)
                    + "_" + this.LanguageTag
                    + this.fileName.substring(this.fileName.length() - 4);
        }
        return result;
    }

    @Override
    public List<Question> getAll() {

        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(this.localedFileName)) {

            if (inputStream == null) {
                throw new QuestionSourceException("File not found: " + this.localedFileName);
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

        if (params.length < 3) {
            throw new QuestionCreationException("There are too few question elements in the line");
        }

        int rightAnswerIndex = Integer.parseInt(params[params.length - 1]);
        int totalAnswerNumber = params.length - 1;

        if (rightAnswerIndex < 1 || rightAnswerIndex > totalAnswerNumber) {
            throw new QuestionCreationException("Incorrect number of the right answer");
        }

        List<Answer> answers = new ArrayList<>();

        IntStream.range(1, totalAnswerNumber).forEach(i ->
                answers.add(new Answer(params[i], i == rightAnswerIndex)));

        return new Question(params[0], answers);

    }
}
