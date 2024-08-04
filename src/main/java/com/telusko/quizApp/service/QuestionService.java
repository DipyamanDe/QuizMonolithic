package com.telusko.quizApp.service;

import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
	
//	private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    @Autowired
    QuestionDao questionDao;

//    public List<Question> getAllQuestions(){
//        logger.info("Fetching all questions from the database");
//        List<Question> questions = questionDao.findAll();
//        logger.info("Retrieved {} questions", questions.size());
//        logger.info("Retrieved question: " + questions);
//
//        
//        return questions;
//    }
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
}