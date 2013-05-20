package com.huskysoft.eduki.test;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import com.huskysoft.eduki.CoursesListActivity;
import com.huskysoft.eduki.LoginActivity;
import com.huskysoft.eduki.MainActivity;
import com.jayway.android.robotium.solo.Solo;

/**
 * 
 * @author Rafael Vertido MainActivityTest tests the Main Activity
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private static final int TIMEOUT = 10000;
    
    /**
     * Constructs the tests
     */
    public MainActivityTest() {
        super(MainActivity.class);
    }
    
    /**
     * Initializes required variables
     */
    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }
    
    /**
     * This is a black box test, making sure the new activity starts after clicking the courses button
     */
    @Test(timeout=TIMEOUT)
    public void testAllCoursesClick() {
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);
        solo.clickOnButton(solo.getString(com.huskysoft.eduki.R.string.coursesList));
        solo.assertCurrentActivity("Did not start the Course list Activity", CoursesListActivity.class);
    }
    
    /**
     * This is a black box test, making sure the new activity starts after clicking the login button
     */
    @Test(timeout=TIMEOUT)
    public void testLoginButtonClick() {
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);
        solo.clickOnButton(solo.getString(com.huskysoft.eduki.R.string.login));
        solo.assertCurrentActivity("Did not start the Login Activity", LoginActivity.class);
    }
}
