package com.santhkumar.quizapp;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final List<Question> questions;

    public QuestionService() {
        Question q1 = new Question();
        q1.setId(1L);
        q1.setQuestionText("What is 2+2?");
        q1.setOption1("3");
        q1.setOption2("4");
        q1.setOption3("5");
        q1.setOption4("6");
        q1.setCorrectAnswer("4");

        Question q2 = new Question();
        q2.setId(2L);
        q2.setQuestionText("What is the capital of France?");
        q2.setOption1("Paris");
        q2.setOption2("London");
        q2.setOption3("Berlin");
        q2.setOption4("Madrid");
        q2.setCorrectAnswer("Paris");

        questions = Arrays.asList(q1, q2);
    }

    public List<QuestionDTO> getAllQuestions() {
        return questions.stream().map(q -> {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(q.getId());
            dto.setQuestionText(q.getQuestionText());
            dto.setOption1(q.getOption1());
            dto.setOption2(q.getOption2());
            dto.setOption3(q.getOption3());
            dto.setOption4(q.getOption4());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<QuestionDTO> getQuestionById(Long id) {
        return questions.stream()
                .filter(q -> q.getId().equals(id))
                .map(q -> {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setId(q.getId());
                    dto.setQuestionText(q.getQuestionText());
                    dto.setOption1(q.getOption1());
                    dto.setOption2(q.getOption2());
                    dto.setOption3(q.getOption3());
                    dto.setOption4(q.getOption4());
                    return dto;
                }).findFirst();
    }

    public boolean checkAnswer(Long questionId, String answer) {
        return questions.stream()
                .filter(q -> q.getId().equals(questionId))
                .map(q -> q.getCorrectAnswer().equalsIgnoreCase(answer))
                .findFirst()
                .orElse(false);
    }
}