package com.huskysoft.eduki;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.eduki.R;
import com.huskysoft.eduki.data.LessonQuery;

public class LessonsViewActivity extends Activity implements TaskComplete {
	
	private String course_id;
	private String course_title;
	private String lesson_id;
	private String lesson_title;
	private String lesson_content;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            course_title = extras.getString("course_title");
            course_id = extras.getString("course_id");
            lesson_title = extras.getString("lesson_title");
            lesson_id = extras.getString("lesson_id");
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
        setContentView(R.layout.activity_lessonview);
        lesson_content = LessonQuery.parseLessonContent(data);
        TextView contentView = (TextView) findViewById(R.id.lessonViewLayoutText);
        contentView.setText(lesson_content);
    }
}
