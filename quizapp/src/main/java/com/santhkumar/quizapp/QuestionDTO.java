package com.santhkumar.quizapp;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}