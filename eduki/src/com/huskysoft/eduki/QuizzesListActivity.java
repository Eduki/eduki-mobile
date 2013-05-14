package com.huskysoft.eduki;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.Quiz;
import com.huskysoft.eduki.data.QuizQuery;

public class QuizzesListActivity extends Activity implements TaskComplete {
    /**
     * quizList - The list of quizzes to be displayed, 
     * not initialized until data has been loaded
     * 
     * course - The specific course that the lesson is tied to, this is
     * initialized after reading in the shared data from the CoursesListActivity
     */
    private List<Quiz> quizList;
    private Course course;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String course_title = extras.getString("course_title");
            int course_id = extras.getInt("course_id");
            course = new Course(course_id, course_title);
            QuizQuery.getAllQuizzes(this, course_id);
        }
        setContentView(R.layout.loading_screen);
    } 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void taskComplete(String data) {
        // Get the list of lessons, and set the title
        quizList = QuizQuery.parseQuizzesList(data);
        this.setTitle(course.getTitle());
        
        // Check if there are lessons, if there are then display them in a list,
        // otherwise, display a message saying that no lessons were found.
        if (quizList.size() == 0) {
            setContentView(R.layout.activity_no_list_found);
            TextView contentView = (TextView) findViewById(R.id.noListText);
            contentView.setText("No quizzes found for this course.");
        } else {
            setContentView(R.layout.activity_quizzeslist);
            ArrayAdapter<Quiz> adapter = new ArrayAdapter<Quiz>(this, 
                    android.R.layout.simple_list_item_1, quizList);
            ListView listView = (ListView) findViewById(R.id.quizzesListView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    QuizzesListActivity.this.quizSelected(position);
                }
            });
        }
    }
    
    /**
     * Will use the position parameter and find that course in the list of quizzes,
     * calling the quiz view activity.
     * @param position the position in the list of the button pressed
     */
    private void quizSelected(int position) {
        Quiz chosen = quizList.get(position);
        Intent i = new Intent(this, QuizzesViewActivity.class);
        i.putExtra("quiz_title", chosen.getTitle());
        i.putExtra("quiz_id", chosen.getId());
        i.putExtra("course_id", course.getId());
        startActivity(i);
    }
}
