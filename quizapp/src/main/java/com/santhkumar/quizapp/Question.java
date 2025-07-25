package com.santhkumar.quizapp;

import lombok.Data;

@Data
public class Question {
    private Long id;
    private String section;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
}