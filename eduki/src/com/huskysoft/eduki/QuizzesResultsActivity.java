
package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import com.huskysoft.eduki.data.ConnectionTask;
import com.huskysoft.eduki.data.Quiz;
import com.huskysoft.eduki.data.QuizQuery;
import com.huskysoft.eduki.data.ViewPopulator;

/**
 * @author Rafael Vertido Class QuizzesResultsActivity shows a specific quiz
 *         result, including the grading and navigation options.
 */

public class QuizzesResultsActivity extends Activity implements TaskComplete {

    private int questionsAnswered;
    private int questionsCorrect;
    private int quiz_id;
    private int course_id;
    private String quiz_title;
    private LinearLayout mainLayout;
    private List<Quiz> quizList;

    /** Save 'this' for access to nested classes */
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ConnectionTask.isOnline(this)) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                questionsAnswered = extras.getInt("questionsAnswered");
                questionsCorrect = extras.getInt("questionsCorrect");
                quiz_title = extras.getString("quiz_title");
                quiz_id = extras.getInt("quiz_id");
                course_id = extras.getInt("course_id");
                QuizQuery.getAllQuizzes(this, course_id, quiz_id);
            }
            mainLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_quizzesresult,
                    null);
            setContentView(R.layout.loading_screen);
        } else {
            Intent intent = new Intent(this, NoConnectivityActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void taskComplete(String data, int id) {
        // TODO Auto-generated method stub
        // setContentView(R.layout.activity_quizzesresult);

        quizList = QuizQuery.parseQuizzesList(data);
        LinearLayout layout = (LinearLayout) mainLayout.findViewById(R.id.quiz_rowview);
        View.OnClickListener v = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizSelected(v.getId());
            }
        };
        Iterator<Quiz> it = quizList.iterator();
        Quiz chosen = null;
        while (it.hasNext()) {
            Quiz next = it.next();
            if (next.getId() == quiz_id) {
                chosen = next;
                it.remove();
                break;
            }
        }
        ViewPopulator.populateCarouselWithSelected(quizList, layout, R.layout.yellow_carousel_item,
                v, this, chosen);

        setContentView(mainLayout);
        ((TextView) findViewById(R.id.title)).setText(R.string.quizResults);
        ((TextView) findViewById(R.id.subtitle)).setText(R.string.quizzesTitle);
        displayQuizResults();
    }

    /**
     * Display the number of questions the user answered correctly and also
     * displays their percentage rounded to the nearest percent.
     */
    private void displayQuizResults() {
        // Calculate score and display to the user
        TextView contentView = (TextView) findViewById(R.id.quizResultText);
        double percentage = ((1.0 * questionsCorrect) / questionsAnswered) * 100; // Get
                                                                                  // %
                                                                                  // score
        contentView.setText("Quiz Finished!\n" + "You got "
                + questionsCorrect + "/" + questionsAnswered + " questions correct.\n"
                + "Your score is " + (int) percentage + "%!");

        // Add event listeners to the buttons in this view
        addResultsEventListeners();
    }

    /** 
     * Attaches event listeners to the navigation buttons 
     */
    private void addResultsEventListeners() {
        ImageButton retakeButton = (ImageButton) findViewById(R.id.retakeButton);
        ImageButton coursesButton = (ImageButton) findViewById(R.id.coursesButton);

        // On mouse click event listener for the re-take button
        retakeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                retakeQuiz();
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
     * Restarts the quiz activity to the beginning
     */
    private void retakeQuiz() {
        // TODO: ADD POSTING OF RESULTS TO QUIZ_ATTEMPT
        Intent intent = new Intent(context, QuizzesViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("quiz_title", quiz_title);
        intent.putExtra("quiz_id", quiz_id);
        intent.putExtra("course_id", course_id);
        startActivity(intent);
    }
    
    /**
     * Restarts to the course list activity
     */
    private void restartToCoursesActivity() {
        Intent intent = new Intent(context, CoursesListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Will use the position parameter and find that course in the list of
     * quizzes, calling the quiz view activity.
     * 
     * @param position the position in the list of the button pressed
     */
    private void quizSelected(int position) {
        Quiz chosen = quizList.get(position);
        Intent i = new Intent(this, QuizzesViewActivity.class);
        i.putExtra("quiz_title", chosen.getTitle());
        i.putExtra("quiz_id", chosen.getId());
        i.putExtra("course_id", chosen.getCourseId());
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_courses:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, CoursesListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_dash:
                Intent intentMain = new Intent(this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
