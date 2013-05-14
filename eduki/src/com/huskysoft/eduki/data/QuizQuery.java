package com.huskysoft.eduki.data;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huskysoft.eduki.TaskComplete;

public class QuizQuery {
    // TODO: COMMENT
    public static void getAllQuizzes(TaskComplete callback, int course_id) {
        String quizzesURL = UrlConstants.getAllQuizzesURL(course_id);
        new ConnectionTask(callback).execute(quizzesURL);
    }
    
    // TODO: COMMENT
    public static void getAllQuestions(TaskComplete callback, int course_id, int quiz_id) {
        String singleQuizURL = UrlConstants.getSingleQuizURL(course_id, quiz_id);
        new ConnectionTask(callback).execute(singleQuizURL);
    }
    
    // TODO: COMMENT
    public static List<Quiz> parseQuizzesList(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Quiz>>(){}.getType();
        List<Quiz> quizzes = gson.fromJson(data, collectionType);
        return quizzes;
    }
    
    // TODO: COMMENT
    public static List<Question> parseQuestionsList(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Question>>(){}.getType();
        List<Question> questions = gson.fromJson(data, collectionType);
        return questions;
    }
}
