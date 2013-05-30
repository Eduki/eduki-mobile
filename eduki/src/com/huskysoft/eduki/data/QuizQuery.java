package com.huskysoft.eduki.data;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huskysoft.eduki.TaskComplete;

/**
 * @author Rafael Vertido QuizQuery will make queries related to quizzes to
 *         the api
 */
public class QuizQuery {
    /**
     * Will get a list of all quizzes, returned to the callback as a string
     * 
     * @param callback The callback for when the request is complete
     * @param course_id id of the associated course
     */
    public static void getAllQuizzes(TaskComplete callback, int course_id, int id) {
        String quizzesURL = UrlConstants.getAllQuizzesURL(course_id);
        new ConnectionTask(callback, id).execute(quizzesURL);
    }
    
    /**
     * Will get a list of all questions for a specific quiz
     * 
     * @param callback The callback for when the request is complete
     * @param quiz_id id of the associated quiz
     */
    public static void getAllQuestions(TaskComplete callback, int quiz_id, int id) {
        String singleQuizURL = UrlConstants.getSingleQuizURL(quiz_id);
        new ConnectionTask(callback, id).execute(singleQuizURL);
    }
    
    /**
     * Will parse the string and return a list of quizzes represented in the
     * string.
     * 
     * @param data The json string to be parsed
     * @return A list of quizzes represented in the string
     */
    public static List<Quiz> parseQuizzesList(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Quiz>>(){}.getType();
        List<Quiz> quizzes = gson.fromJson(data, collectionType);
        return quizzes;
    }
    
    /**
     * Will parse the string and return a QuizContent that was represented in the
     * string. The QuizContent returned will include material about the quiz,
     * including the questions tied to the specific quiz
     * 
     * @param data The json string to be parsed
     * @return A QuizContent object that holds the data for a specific quiz
     */
    public static QuizContent parseQuestionsList(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<QuizContent>(){}.getType();
        QuizContent quizContent = gson.fromJson(data, collectionType);
        return quizContent;
    }
}
