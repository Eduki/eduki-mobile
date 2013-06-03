
package com.huskysoft.eduki;

import com.huskysoft.eduki.data.ConnectionTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author Rafael Vertido This activity is for when no internet is available.
 *         Displays an error message.
 */

public class NoConnectivityActivity extends Activity {

    /** Save 'this' for access to nested classes */
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connectivity);
        ((TextView) findViewById(R.id.title)).setText(R.string.noConnection);
        TextView contentView = (TextView) findViewById(R.id.noConnectivityText);
        contentView.setText("Could not submit request due to lack of internet connectivity. " +
                "Please check your connection and try again.");
        addResultsEventListeners();
    }

    /**
     * Attaches event listeners to the navigation buttons
     */
    private void addResultsEventListeners() {
        ImageButton retakeButton = (ImageButton) findViewById(R.id.homeButton);
        ImageButton coursesButton = (ImageButton) findViewById(R.id.coursesButton);

        // On mouse click event listener for the re-take button
        retakeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        // On mouse click event listener for the courses logo
        coursesButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                restartToCoursesActivity();
            }
        });
    }

    /**
     * Restarts to the main login screen
     */
    public void startLogin() {
        if (ConnectionTask.isOnline(this)) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * Restarts to the course list activity
     */
    private void restartToCoursesActivity() {
        if (ConnectionTask.isOnline(this)) {
            Intent intent = new Intent(context, CoursesListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
