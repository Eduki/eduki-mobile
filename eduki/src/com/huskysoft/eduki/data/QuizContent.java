package com.huskysoft.eduki.data;

import java.util.Collections;
import java.util.List;

/**
 * @author Rafael Vertido QuizContent represents the meat of an actual quiz.
 * Has a quiz id, course id, title, and a list of Problems.
 */
public class QuizContent {
    
    private int id;
    private int course_id;
    private String title;
    private List<Problem> problems;
    
    /** Creates a new QuizContent */
    public QuizContent(int id, int course_id, String title, List<Problem> problems) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.problems = problems;
    }
    
    /** @return the string representation of this quizContent */
    public String toString() {
        return getTitle();
    }
    
    /** @return id of the quizContent */
    public int getId() {
        return id;
    }
    
    /** @return id of the course associated with this quizContent */    
    public int getCourseId() {
        return course_id;
    }
    
    /** @return title of this quizContent */
    public String getTitle() {
        return title;
    }
    
    /** @return List of Problem objects associated with this quizContent */
    public List<Problem> getProblems() {
        return Collections.unmodifiableList(problems);
    }
    
    /**
     * @author Rafael Vertido Problem represents the information for a single quiz question
     */
    public static class Problem {
        
        private int id;
        private int quiz_id;
        private String question;
        private String answer;
        
        /** Creates a new Problem */
        public Problem(int id, int quiz_id, String question, String answer) {
            this.id = id;
            this.quiz_id = quiz_id;
            this.question = question;
            this.answer = answer;
        }
        
        /** @return String representation of the question */
        public String toString() {
            return getQuestion();
        }
        
        /** @return question in string form */
        public String getQuestion() {
            return question;
        }
        
        /** @return answer in string form */
        public String getAnswer() {
            return answer;
        }
        
        /** @return id of the Problem */
        public int getId() {
            return id;
        }
        
        /** @return id of the quiz */
        public int getQuizId() {
            return quiz_id;
        }
    }
}
