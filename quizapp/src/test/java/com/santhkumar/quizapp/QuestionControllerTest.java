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
    public void testGetQuestionsBySection_Programming() {
        ResponseEntity<QuestionDTO[]> response = restTemplate.getForEntity("/question/section/Programming", QuestionDTO[].class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(10, response.getBody().length);
        assertEquals("What is the output of `System.out.println(1 + 2 + \"3\");` in Java?", response.getBody()[0].getQuestionText());
    }

    @Test
    public void testGetQuestionsBySection_Aptitude() {
        ResponseEntity<QuestionDTO[]> response = restTemplate.getForEntity("/question/section/Aptitude", QuestionDTO[].class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(10, response.getBody().length);
    }

    @Test
    public void testGetQuestionsBySection_EnglishGrammar() {
        ResponseEntity<QuestionDTO[]> response = restTemplate.getForEntity("/question/section/English Grammar", QuestionDTO[].class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(10, response.getBody().length);
    }

    @Test
    public void testGetQuestionById() {
        ResponseEntity<QuestionDTO> response = restTemplate.getForEntity("/question/1", QuestionDTO.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("What is the output of `System.out.println(1 + 2 + \"3\");` in Java?", response.getBody().getQuestionText());
    }

    @Test
    public void testGetQuestionById_NotFound() {
        ResponseEntity<QuestionDTO> response = restTemplate.getForEntity("/question/999", QuestionDTO.class);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void testSubmitAnswer_Correct() {
        ResponseEntity<String> response = restTemplate.postForEntity("/question/submit?questionId=1&answer=123", null, String.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Correct!", response.getBody());
    }

    @Test
    public void testSubmitAnswer_Incorrect() {
        ResponseEntity<String> response = restTemplate.postForEntity("/question/submit?questionId=1&answer=33", null, String.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Incorrect!", response.getBody());
    }
}