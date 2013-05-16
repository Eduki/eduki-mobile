
package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.huskysoft.eduki.R;

/**
 * @author Cody Thomas MainActivity is the first activity to be loaded when the
 *         application begins. It gives a choice of options for which the user
 *         can continue, such as a button for the list of courses
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: If authenticated, get rid of the login bar. 
        //       In this case, switch it to a logout bar and change the onclick listener
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * An onClickListener established in the xml of this activity, called when
     * the "courses list" button is pressed. Note: Method header must be public
     * for the xml to recognize this method.
     * 
     * @param view The view clicked. In this case, a button
     */
    public void coursesListPressed(View view) {
        Intent intent = new Intent(this, CoursesListActivity.class);
        startActivity(intent);
    }
    
    /**
     * An onClickListener established in the xml of this activity, called when
     * the "login" button is pressed. Note: Method header must be public
     * for the xml to recognize this method.
     * 
     * @param view The view clicked. In this case, a button
     */
    public void loginPressed(View view) {
        
    }
}
