package com.huskysoft.eduki.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import android.test.AndroidTestCase;

import com.huskysoft.eduki.data.QuizContent;
import com.huskysoft.eduki.data.QuizContent.Problem;

public class QuizContentTest extends AndroidTestCase {
    private QuizContent qc;
    
    /** Fields for the QuizContent object */
    private int id;
    private int course_id;
    private String title;
    private List<Problem> problems;
    
    /** Fields for the Problem */
    private Problem p;
    private int problem_id;
    private int problem_quiz_id;
    private String problem_question;
    private String problem_answer;
    
    /**
     * Creates a QuizContent
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        // Set up QuizContent fields
        title = "QUIZ_CONTENT_TEST_TITLE";
        id = 5;
        course_id = 5000;
        problems = new ArrayList<Problem>();
        
        // Set up Problem fields
        problem_id = 9000;
        problem_quiz_id = 1;
        problem_question = "EXAMPLE QUESTION? A. YES B. NO";
        problem_answer = "C.";
        p = new Problem(problem_id, problem_quiz_id, problem_question, problem_answer);
        problems.add(p);
        
        // set the quiz content
        qc = new QuizContent(id, course_id, title, problems);
    }
    
    /** Tests the toString method  */
    public void testToString() {
        assertEquals(title, qc.toString());
    }
    
    /** Tests the getId method */
    public void testGetId() {
        assertEquals(id, qc.getId());
    }
    
    /** Tests the getCourseId method */    
    public void testGetCourseId() {
        assertEquals(course_id, qc.getCourseId());
    }
    
    /** Tests the getTitle method */
    public void testGetTitle() {
        assertEquals(title, qc.getTitle());
    }
    
    /** tests the getProblems method */
    public void testGetProblems() {
        assertEquals(problems.size(), qc.getProblems().size());
    }
    
    /** Tests toString method of the Problem subclass */
    public void testProblemToString() {
        assertEquals(problem_question, p.toString());
    }
    
    /** Tests getQuestion method of the Problem subclass */
    public void testGetQuestion() {
        assertEquals(problem_question, p.getQuestion());
    }
    
    /** Tests getAnswer method of the Problem subclass */
    public void testGetAnswer() {
        assertEquals(problem_answer, p.getAnswer());
    }
    
    /** Tests getId method of the Problem subclass */
    public void testGetProblemId() {
        assertEquals(problem_id, p.getId());
    }
    
    /** Tests getQuizId method of the Problem subclass */
    public void testGetQuizId() {
        assertEquals(problem_quiz_id, p.getQuizId());
    }
}
