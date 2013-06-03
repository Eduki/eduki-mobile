package com.huskysoft.eduki.test;

import org.junit.Before;
import org.junit.Test;

import com.huskysoft.eduki.data.Quiz;

import android.test.AndroidTestCase;

/**
 * @author Rafael Vertido Test suite for the Quiz object
 */

public class QuizTest extends AndroidTestCase {
    private static final int TIMEOUT = 3000;
    private Quiz quiz;
    private int id;
    private int course_id;
    private String title;
    
    /**
     * Creates a course
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        title = "QUIZ_TEST_TITLE";
        id = 100;
        course_id = 9000;
        quiz = new Quiz(id, course_id, title);
    }

    /**
     * Test the getId method
     */
    @Test(timeout=TIMEOUT)
    public void testGetId() {
        assertEquals("Quiz id incorrect", id, quiz.getId());
    }
    
    /**
     * Test the getTitle method
     */
    @Test(timeout=TIMEOUT)
    public void testGetTitle() {
        assertEquals("Quiz id incorrect", title.toUpperCase(), quiz.getTitle());
    }
    
    /**
     * Test getCourseid method
     */
    @Test(timeout=TIMEOUT)
    public void testGetCoruseId() {
        assertEquals("Quiz id incorrect", course_id, quiz.getCourseId());
    }
}
