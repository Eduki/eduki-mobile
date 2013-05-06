package com.huskysoft.eduki;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.example.eduki.R;
import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.Lesson;
import com.huskysoft.eduki.data.LessonQuery;

/**
 * @author Rafael Vertido Class LessonsListActivity shows a list of all lessons,
 *         allowing them to be clicked.
 */

public class LessonsListActivity extends Activity implements TaskComplete {
    /**
     * lessonList - The list of courses to be displayed, 
     * not initialized until data has been loaded
     * 
     * course - The specific course that the lesson is tied to, this is
     * initialized after reading in the shared data from the CoursesListActivity
     */
	private List<Lesson> lessonList;
	private Course course;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String course_title = extras.getString("course_title");
            int course_id = extras.getInt("course_id");
            course = new Course(course_id, course_title);
            LessonQuery.getAllLessons(this, course_id);
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
        lessonList = LessonQuery.parseLessonsList(data);
        if (lessonList.size() == 0) {
            noLessonFoundError();
        } else {
            setContentView(R.layout.activity_lessonslist);
            this.setTitle(course.getTitle());
            ArrayAdapter<Lesson> adapter = new ArrayAdapter<Lesson>(this, 
                    android.R.layout.simple_list_item_1, lessonList);
            ListView listView = (ListView) findViewById(R.id.lessonsListView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LessonsListActivity.this.lessonSelected(position);
                }
            });
        }
    }
    
    /**
     * Helper method that displays an error message to the user that no lessosn
     * were found for the chosen course. Returns back to the course selection
     * once the dialog is acknowledged.
     */
    private void noLessonFoundError() {
        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
        TextView myMsg = new TextView(this);
        popupBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    LessonsListActivity.super.onBackPressed();
                }
            }); 
        popupBuilder.setTitle("Error");
        myMsg.setText("No lessons found");
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        popupBuilder.setView(myMsg);
        popupBuilder.show();  
    }
    
    /**
     * Will use the position parameter and find that course in the list of lessons,
     * calling the lesson view activity.
     * @param position the position in the list of the button pressed
     */
    private void lessonSelected(int position) {
        Lesson chosen = lessonList.get(position);
        Intent i = new Intent(this, LessonsViewActivity.class);
        i.putExtra("lesson_title", chosen.getTitle());
        i.putExtra("lesson_id", chosen.getId());
        i.putExtra("lesson_body", chosen.getBody());
        i.putExtra("course_id", course.getId());
        startActivity(i);
    }
}
