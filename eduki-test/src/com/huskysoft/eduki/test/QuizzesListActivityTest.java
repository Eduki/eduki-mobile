package com.huskysoft.eduki.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.huskysoft.eduki.LessonsListActivity;
import com.huskysoft.eduki.QuizzesListActivity;
import com.huskysoft.eduki.QuizzesViewActivity;
import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.Quiz;
import com.jayway.android.robotium.solo.Solo;

public class QuizzesListActivityTest extends ActivityInstrumentationTestCase2<QuizzesListActivity> { 
    
    private Solo solo;
    private static final int TIMEOUT = 10000;
    
    public QuizzesListActivityTest() {
        super(QuizzesListActivity.class);
    }
    
    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }
    
    @Override
    public QuizzesListActivity getActivity() {
        Intent i = new Intent();
        i.putExtra("course_title", "test_course_title");
        i.putExtra("course_id", 1);
        setActivityIntent(i);
        return super.getActivity();
    }
    
    /**
     * Assert the list of test quizzes appear properly
     */
    @Test(timeout=TIMEOUT)
    public void testQuizzesAppear() {
        solo.assertCurrentActivity("Wrong activity", QuizzesListActivity.class);
        solo.waitForView(solo.getView(com.huskysoft.eduki.R.id.quizzesListView));
        List<Quiz> quizList = ((QuizzesListActivity) solo.getCurrentActivity()).getQuizList();
        Course course = ((QuizzesListActivity) solo.getCurrentActivity()).getCourse();
        assertNotSame(null, quizList);
        assertNotSame(null, course);
        assertNotSame(quizList.size(), 0);
        assertEquals("test_course_title", course.getTitle());
        assertEquals(1, course.getId());
    }
    
    /**
     * Assert the clicking on a specific quiz works properly
     */
    @Test(timeout=TIMEOUT)
    public void testNewActivityStarted() {
        solo.assertCurrentActivity("Wrong activity", QuizzesListActivity.class);
        solo.waitForView(solo.getView(com.huskysoft.eduki.R.id.quizzesListView));
        List<Quiz> quizList = ((QuizzesListActivity) solo.getCurrentActivity()).getQuizList();
        assertNotSame(quizList.size(), 0);
        solo.clickOnText(quizList.get(0).toString());
        solo.assertCurrentActivity("Wrong activity", QuizzesViewActivity.class);
    }
}
