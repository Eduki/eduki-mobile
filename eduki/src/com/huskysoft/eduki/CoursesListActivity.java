package com.huskysoft.eduki;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import com.example.eduki.R;
import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.CourseQuery;

public class CoursesListActivity extends Activity implements TaskComplete {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Once uncommented, this will add all the courses to the current view
        //CourseQuery.getAllCourses(this);
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
        List<Course> list = CourseQuery.parseCourseList(data);
        ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this, 
                android.R.layout.simple_list_item_1, list);
        setContentView(R.layout.activity_courseslist);
        ListView listView = (ListView) findViewById(R.id.courseListView);
        listView.setAdapter(adapter);
        // TODO: Use list data to propogate the courses list, takedown progress circles
    }
}
