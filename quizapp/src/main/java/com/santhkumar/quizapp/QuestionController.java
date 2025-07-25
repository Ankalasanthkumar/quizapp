package com.santhkumar.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("section/{section}")
    public List<QuestionDTO> getQuestionsBySection(@PathVariable String section) {
        return questionService.getQuestionsBySection(section);
    }

    @GetMapping("{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        Optional<QuestionDTO> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("submit")
    public ResponseEntity<String> submitAnswer(@RequestParam Long questionId, @RequestParam String answer) {
        boolean isCorrect = questionService.checkAnswer(questionId, answer);
        return ResponseEntity.ok(isCorrect ? "Correct!" : "Incorrect!");
    }
}