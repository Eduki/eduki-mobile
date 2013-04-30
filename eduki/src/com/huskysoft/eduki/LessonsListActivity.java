package com.huskysoft.eduki;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eduki.R;
import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.CourseQuery;
import com.huskysoft.eduki.data.Lesson;
import com.huskysoft.eduki.data.LessonQuery;

public class LessonsListActivity extends Activity implements TaskComplete {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        // Once uncommented, this will add all the courses to the current view
        // TODO: Figure out what course is being inspected
        // Course course = //
        // LessonQuery.getAllLessons(this, course);
        // setContentview(R.layout.activity_lessonslist);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void taskComplete(String data) {
        List<Lesson> list = LessonQuery.parseLessonsList(data);
        ArrayAdapter<Lesson> adapter = new ArrayAdapter<Lesson>(this, 
                android.R.layout.simple_list_item_1, list);
        //setContentView(R.layout.activity_lessonslist);
        //ListView listView = (ListView) findViewById(R.id.lessonListView);
        //listView.setAdapter(adapter);
        // TODO: Use list data to propogate the lessons list,
        // takedown progress circles
    }
}
