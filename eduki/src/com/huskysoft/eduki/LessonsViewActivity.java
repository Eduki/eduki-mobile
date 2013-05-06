package com.huskysoft.eduki;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.widget.TextView;

import com.example.eduki.R;
import com.huskysoft.eduki.data.Lesson;
import com.huskysoft.eduki.data.LessonQuery;

/**
 * @author Rafael Vertido Class LessonsViewActivity shows a specific lesson's content
 */

public class LessonsViewActivity extends Activity implements TaskComplete {
	
	/** Specific lesson this view is tied to */
	private Lesson lesson;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String lesson_title = extras.getString("lesson_title");
            String lesson_body = extras.getString("lesson_body");
            int lesson_id = extras.getInt("lesson_id");          
            int course_id = extras.getInt("course_id");
            lesson = new Lesson(lesson_id, lesson_title, course_id, lesson_body);
            LessonQuery.getSpecificLesson(this, course_id, lesson_id);
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
        String lessonBody = lesson.getBody();
        if (lessonBody.length() == 0) {
            noLessonFoundError();
        } else {
            setContentView(R.layout.activity_lessonview);
            this.setTitle(lesson.getTitle());
            TextView contentView = (TextView) findViewById(R.id.lessonViewLayoutText);
            contentView.setText(lessonBody);
        }
    }
    
    /**
     * Helper method that displays an error message to the user that no lesson
     * body was found for the chosen lesson. Returns back to the lesson selection
     * once the dialog is acknowledged.
     */
    private void noLessonFoundError() {
        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
        TextView myMsg = new TextView(this);
        popupBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    LessonsViewActivity.super.onBackPressed();
                }
            }); 
        popupBuilder.setTitle("Error");
        myMsg.setText("No lesson body");
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        popupBuilder.setView(myMsg);
        popupBuilder.show();  
    }
}
