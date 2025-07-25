package com.santhkumar.quizapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final List<Question> questions;

    public QuestionService() {
        questions = new ArrayList<>();

        // Programming Questions (Java/SQL, 10)
        questions.add(createQuestion(1L, "Programming", "What is the output of `System.out.println(1 + 2 + \"3\");` in Java?", "33", "123", "5", "6", "123"));
        questions.add(createQuestion(2L, "Programming", "Which SQL keyword is used to retrieve unique records?", "DISTINCT", "UNIQUE", "SELECT", "GROUP BY", "DISTINCT"));
        questions.add(createQuestion(3L, "Programming", "In Java, which keyword prevents method overriding?", "final", "static", "private", "abstract", "final"));
        questions.add(createQuestion(4L, "Programming", "What does SQL's `JOIN` clause do?", "Combines rows from two or more tables", "Filters rows", "Sorts data", "Groups data", "Combines rows from two or more tables"));
        questions.add(createQuestion(5L, "Programming", "In Java, what is the default value of an uninitialized int?", "0", "null", "-1", "1", "0"));
        questions.add(createQuestion(6L, "Programming", "Which SQL function calculates the total of a numeric column?", "SUM()", "COUNT()", "AVG()", "MAX()", "SUM()"));
        questions.add(createQuestion(7L, "Programming", "In Java, which collection allows duplicate elements?", "List", "Set", "Map", "Queue", "List"));
        questions.add(createQuestion(8L, "Programming", "In SQL, what does `WHERE` clause do?", "Filters rows", "Joins tables", "Sorts data", "Groups data", "Filters rows"));
        questions.add(createQuestion(9L, "Programming", "In Java, what is used to handle exceptions?", "try-catch", "if-else", "switch-case", "for-loop", "try-catch"));
        questions.add(createQuestion(10L, "Programming", "Which SQL command modifies data in a table?", "UPDATE", "INSERT", "DELETE", "SELECT", "UPDATE"));

        // Aptitude Questions (Advanced, 10)
        questions.add(createQuestion(11L, "Aptitude", "If a train travels 120 km in 2 hours, how far will it travel in 5 hours at the same speed?", "300 km", "240 km", "360 km", "280 km", "300 km"));
        questions.add(createQuestion(12L, "Aptitude", "What is 15% of 250?", "37.5", "35", "40", "45", "37.5"));
        questions.add(createQuestion(13L, "Aptitude", "If 4 workers complete a task in 12 days, how many days will 6 workers take?", "8 days", "9 days", "10 days", "7 days", "8 days"));
        questions.add(createQuestion(14L, "Aptitude", "What is the next number in the series: 3, 9, 27, 81?", "243", "162", "216", "324", "243"));
        questions.add(createQuestion(15L, "Aptitude", "If A is 20% taller than B, and B is 150 cm, how tall is A?", "180 cm", "170 cm", "190 cm", "175 cm", "180 cm"));
        questions.add(createQuestion(16L, "Aptitude", "What is the compound interest on $1000 at 5% per annum for 2 years?", "$102.50", "$100", "$105", "$110.25", "$110.25"));
        questions.add(createQuestion(17L, "Aptitude", "If a shirt costs $30 after a 25% discount, what was the original price?", "$40", "$35", "$45", "$38", "$40"));
        questions.add(createQuestion(18L, "Aptitude", "What is the LCM of 12 and 18?", "36", "24", "48", "72", "36"));
        questions.add(createQuestion(19L, "Aptitude", "If 3 books cost $45, how much do 5 books cost?", "$75", "$60", "$70", "$80", "$75"));
        questions.add(createQuestion(20L, "Aptitude", "A boat travels 60 km downstream in 3 hours and upstream in 5 hours. What is the speed of the current?", "4 km/h", "3 km/h", "5 km/h", "6 km/h", "4 km/h"));

        // English Grammar Questions (10)
        questions.add(createQuestion(21L, "English Grammar", "Which sentence is grammatically correct?", "She runs quickly.", "She run quick.", "She running quickly.", "She runs quick.", "She runs quickly."));
        questions.add(createQuestion(22L, "English Grammar", "What is the plural form of 'child'?", "Children", "Childs", "Childes", "Childrens", "Children"));
        questions.add(createQuestion(23L, "English Grammar", "Which word is a pronoun?", "He", "Run", "Big", "Table", "He"));
        questions.add(createQuestion(24L, "English Grammar", "What is the past tense of 'go'?", "Went", "Gone", "Goes", "Going", "Went"));
        questions.add(createQuestion(25L, "English Grammar", "Which is a correct sentence?", "They are playing football.", "They is playing football.", "They plays football.", "They playing football.", "They are playing football."));
        questions.add(createQuestion(26L, "English Grammar", "What is the comparative form of 'good'?", "Better", "Gooder", "Best", "More good", "Better"));
        questions.add(createQuestion(27L, "English Grammar", "Which word is an adverb?", "Quickly", "Fast", "Quick", "Speed", "Quickly"));
        questions.add(createQuestion(28L, "English Grammar", "What is the correct form of the verb in: 'She ___ (sing) beautifully.'?", "sings", "sing", "singing", "sang", "sings"));
        questions.add(createQuestion(29L, "English Grammar", "Which sentence uses correct punctuation?", "I have two cats, a dog, and a bird.", "I have two cats a dog and a bird.", "I have two cats, a dog and a bird.", "I have two cats a dog, and a bird.", "I have two cats, a dog, and a bird."));
        questions.add(createQuestion(30L, "English Grammar", "What is the superlative form of 'bad'?", "Worst", "Badder", "Badest", "More bad", "Worst"));
    }

    private Question createQuestion(Long id, String section, String questionText, String option1, String option2, String option3, String option4, String correctAnswer) {
        Question q = new Question();
        q.setId(id);
        q.setSection(section);
        q.setQuestionText(questionText);
        q.setOption1(option1);
        q.setOption2(option2);
        q.setOption3(option3);
        q.setOption4(option4);
        q.setCorrectAnswer(correctAnswer);
        return q;
    }

    public List<QuestionDTO> getQuestionsBySection(String section) {
        return questions.stream()
                .filter(q -> q.getSection().equals(section))
                .map(q -> {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setId(q.getId());
                    dto.setSection(q.getSection());
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
                    dto.setSection(q.getSection());
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