
package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import com.huskysoft.eduki.data.ConnectionTask;
import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.CourseQuery;

/**
 * @author Cody Thomas Class CoursesListActivity shows a list of all courses,
 *         allowing them to be clicked.
 */

public class CoursesListActivity extends Activity implements TaskComplete {
    /**
     * The list of courses to be displayed, not initialized until data has been
     * loaded
     */
    private List<Course> courseList;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ConnectionTask.isOnline(this)) {
            CourseQuery.getAllCourses(this, 0);
            setContentView(R.layout.loading_screen);
        } else {
            ConnectionTask.startNoConnectivityActivity(this);
        }
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_courses).setVisible(false);
        return true;
    }

    /**
     * 
     * @return The current list of courses in use
     */
    public List<Course> getCourseList() {
        return Collections.unmodifiableList(courseList);
    }
    
    @Override
    public void taskComplete(String data, int id) {
        ConnectionTask.checkErrors(this, data);
        courseList = CourseQuery.parseCourseList(data);
        ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this,
                R.layout.list_item_layout, courseList);
        setContentView(R.layout.activity_courseslist);
        ((TextView) findViewById(R.id.title)).setText("COURSES");
        ListView listView = (ListView) findViewById(R.id.courseListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoursesListActivity.this.courseSelected(position);
            }
        });
    }

    /**
     * Will use the position parameter and find that course in the list of courses,
     * calling the lessons list activity.
     * @param position the position in the list of the button pressed
     */
    private void courseSelected(int position) {
        Course chosen = courseList.get(position);
        Intent i = new Intent(context, CourseActivity.class);
        i.putExtra("course_title", chosen.getTitle());
        i.putExtra("course_id", chosen.getId());
        i.putExtra("course_description", chosen.getDescription());
        startActivity(i);
    }
}
