package com.huskysoft.eduki;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.eduki.R;
import com.huskysoft.eduki.data.LessonQuery;

public class LessonsViewActivity extends Activity implements TaskComplete {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        // TODO: Figure out how to get the specific lesson
        // Once uncommented, this will add all the specific lesson to the current view
        // Lesson lesson = // Get lesson
        // LessonQuery.getSpecificLesson(this, lesson);
        // setContentView(R.layout.activity_lessonview);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void taskComplete(String data) {
        // TODO: Set view for single lesson
    }
}
