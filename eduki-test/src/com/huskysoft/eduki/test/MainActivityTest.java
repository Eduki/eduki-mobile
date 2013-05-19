package com.huskysoft.eduki.test;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import com.huskysoft.eduki.CoursesListActivity;
import com.huskysoft.eduki.LoginActivity;
import com.huskysoft.eduki.MainActivity;
import com.jayway.android.robotium.solo.Solo;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private static final int TIMEOUT = 10000;
    
    public MainActivityTest() {
        super(MainActivity.class);
    }
    
    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }
    
    /**
     * This is a black box test, making sure the new activity starts after clicking a button
     */
    @Test(timeout=TIMEOUT)
    public void testAllCoursesClick() {
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);
        solo.clickOnButton(solo.getString(com.huskysoft.eduki.R.string.coursesList));
        solo.assertCurrentActivity("Did not start the Course list Activity", CoursesListActivity.class);
    }
    
    @Test(timeout=TIMEOUT)
    public void testLoginButtonClick() {
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);
        solo.clickOnButton(solo.getString(com.huskysoft.eduki.R.string.login));
        solo.assertCurrentActivity("Did not start the Login Activity", LoginActivity.class);
    }
}
