package com.huskysoft.eduki.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.huskysoft.eduki.LessonsListActivity;
import com.huskysoft.eduki.LessonsViewActivity;
import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.Lesson;
import com.jayway.android.robotium.solo.Solo;

public class LessonsListActivityTest extends ActivityInstrumentationTestCase2<LessonsListActivity> {
    
    private Solo solo;
    private static final int TIMEOUT = 10000;
    
    public LessonsListActivityTest() {
        super(LessonsListActivity.class);
    }
    
    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }
    
    @Override
    public LessonsListActivity getActivity() {
        Intent i = new Intent();
        i.putExtra("course_title", "test_course_title");
        i.putExtra("course_id", 1);
        setActivityIntent(i);
        return super.getActivity();
    }
    
    /**
     * Assert the list of test lessons appear properly
     */
    @Test(timeout=TIMEOUT)
    public void testLessonsAppear() {
        solo.assertCurrentActivity("Wrong activity", LessonsListActivity.class);
        solo.waitForView(solo.getView(com.huskysoft.eduki.R.id.lessonsListView));
        List<Lesson> lessonList = ((LessonsListActivity) solo.getCurrentActivity()).getLessonList();
        Course course = ((LessonsListActivity) solo.getCurrentActivity()).getCourse();
        assertNotSame(null, lessonList);
        assertNotSame(null, course);
        assertNotSame(lessonList.size(), 0);
        assertEquals("test_course_title", course.getTitle());
        assertEquals(1, course.getId());
    }
    
    /**
     * Assert the clicking on a specific lesson works properly
     */
    @Test(timeout=TIMEOUT)
    public void testNewActivityStarted() {
        solo.assertCurrentActivity("Wrong activity", LessonsListActivity.class);
        solo.waitForView(solo.getView(com.huskysoft.eduki.R.id.lessonsListView));
        List<Lesson> lessonList = ((LessonsListActivity) solo.getCurrentActivity()).getLessonList();
        assertNotSame(lessonList.size(), 0);
        solo.clickOnText(lessonList.get(0).toString());
        solo.assertCurrentActivity("Wrong activity", LessonsViewActivity.class);
    }
}
