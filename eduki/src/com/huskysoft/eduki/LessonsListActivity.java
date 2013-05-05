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
import android.widget.AdapterView.OnItemClickListener;

import com.example.eduki.R;
import com.huskysoft.eduki.data.Lesson;
import com.huskysoft.eduki.data.LessonQuery;

public class LessonsListActivity extends Activity implements TaskComplete {
	
	private List<Lesson> lessonList;
	private String course_id;
	private String course_title;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            course_title = extras.getString("course_title");
            course_id = extras.getString("course_id");
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
        setContentView(R.layout.activity_lessonslist);
        lessonList = LessonQuery.parseLessonsList(data);
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
    
    private void lessonSelected(int position) {
        Lesson chosen = lessonList.get(position);
        Intent i = new Intent(this, LessonsViewActivity.class);
        i.putExtra("lesson_title", chosen.getTitle());
        i.putExtra("lesson_id", chosen.getId());
        startActivity(i);
    }
}
