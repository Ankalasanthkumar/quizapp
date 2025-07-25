package com.santhkumar.quizapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllQuestions() {
        ResponseEntity<QuestionDTO[]> response = restTemplate.getForEntity("/question/allQuestions", QuestionDTO[].class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
        assertEquals("What is 2+2?", response.getBody()[0].getQuestionText());
        assertEquals("What is the capital of France?", response.getBody()[1].getQuestionText());
    }

    @Test
    public void testGetQuestionById() {
        ResponseEntity<QuestionDTO> response = restTemplate.getForEntity("/question/1", QuestionDTO.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("What is 2+2?", response.getBody().getQuestionText());
    }

    @Test
    public void testGetQuestionById_NotFound() {
        ResponseEntity<QuestionDTO> response = restTemplate.getForEntity("/question/999", QuestionDTO.class);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void testSubmitAnswer_Correct() {
        ResponseEntity<String> response = restTemplate.postForEntity("/question/submit?questionId=1&answer=4", null, String.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Correct!", response.getBody());
    }

    @Test
    public void testSubmitAnswer_Incorrect() {
        ResponseEntity<String> response = restTemplate.postForEntity("/question/submit?questionId=1&answer=3", null, String.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Incorrect!", response.getBody());
    }
}