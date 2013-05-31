package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuizzesResultsActivity  extends Activity {

    private int questionsAnswered;
    private int questionsCorrect;
    private String quiz_title;
    private int quiz_id;
    private int course_id;
    
    /** Save 'this' for access to nested classes */
    private final Context context = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionsAnswered = extras.getInt("questionsAnswered");
            questionsCorrect = extras.getInt("questionsCorrect");
            quiz_title = extras.getString("quiz_title");
            quiz_id = extras.getInt("quiz_id");
            course_id = extras.getInt("course_id");
        }
        setContentView(R.layout.activity_quizzesresult);         
        ((TextView) findViewById(R.id.title)).setText(R.string.quizResults);
        displayQuizResults();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void displayQuizResults() {       
        // Calculate score and display to the user
        TextView contentView = (TextView) findViewById(R.id.quizResultText);
        double percentage = ((1.0 * questionsCorrect) / questionsAnswered) * 100; // Get % score
        contentView.setText("Quiz Finished!\n" + "You got "
                            + questionsCorrect + "/" + questionsAnswered + " questions correct.\n"
                            + "Your score is " + (int) percentage + "%!");
        
        // Add event listeners to the buttons in this view
        addResultsEventListeners();
    }
    
    private void addResultsEventListeners() {
        ImageButton retakeButton = (ImageButton) findViewById(R.id.retakeButton);
        ImageButton coursesButton = (ImageButton) findViewById(R.id.coursesButton);
        
        // On mouse click event listener for the re-take button
        retakeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: ADD POSTING OF RESULTS TO QUIZ_ATTEMPT
                Intent intent = new Intent(context, QuizzesViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("quiz_title", quiz_title);
                intent.putExtra("quiz_id", quiz_id);
                intent.putExtra("course_id", course_id);
                startActivity(intent);
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
    
    public void restartToCoursesActivity() {
        Intent intent = new Intent(context, CoursesListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
