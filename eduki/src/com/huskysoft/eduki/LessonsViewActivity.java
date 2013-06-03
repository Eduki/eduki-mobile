
package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import com.huskysoft.eduki.data.ConnectionTask;
import com.huskysoft.eduki.data.Lesson;
import com.huskysoft.eduki.data.LessonQuery;
import com.huskysoft.eduki.data.Quiz;
import com.huskysoft.eduki.data.ViewPopulator;

/**
 * @author Rafael Vertido Class LessonsViewActivity shows a specific lesson's
 *         content
 */

public class LessonsViewActivity extends Activity implements TaskComplete {

    /** Specific lesson this view is tied to */
    private Lesson lesson;
    private List<Lesson> lessonList;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ConnectionTask.isOnline(this)) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String lesson_title = extras.getString("lesson_title");
                String lesson_body = extras.getString("lesson_body");
                int lesson_id = extras.getInt("lesson_id");
                int course_id = extras.getInt("course_id");
                lesson = new Lesson(lesson_id, lesson_title, course_id, lesson_body);
                Log.e("Lesson Body: " + lesson.getBody(), "Lesson Body: " + lesson.getBody());
                mainLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_lessonview,
                        null);
                String lessonBody = lesson.getBody();
                ((TextView) mainLayout.findViewById(R.id.title)).setText(lesson.getTitle());
                TextView contentView = (TextView) mainLayout.findViewById(R.id.lessonViewLayoutText);
    
                // Check if there was useful content, if there was display it,
                // otherwise, display a message that there is no body found.
                if (lessonBody.equals("")) {
                    contentView.setText("No body found for this lesson");
                } else {
                    contentView.setText(lessonBody);
                }
                LessonQuery.getAllLessons(this, course_id, 0);
            }
            setContentView(R.layout.loading_screen);
        } else {
            ConnectionTask.startNoConnectivityActivity(this);
        }
    }

    /**
     * @return A copy of the lesson attached to this lessonView
     */
    public Lesson getLesson() {
        return new Lesson(lesson.getId(), lesson.getTitle(), lesson.getCourseId(), lesson.getBody());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Will use the position parameter and find that course in the list of
     * lessons, calling the lesson view activity.
     * 
     * @param position the position in the list of the button pressed
     */
    private void lessonSelected(int position) {
        Lesson chosen = lessonList.get(position);
        Intent i = new Intent(this, LessonsViewActivity.class);
        i.putExtra("lesson_title", chosen.getTitle());
        i.putExtra("lesson_id", chosen.getId());
        i.putExtra("lesson_body", chosen.getBody());
        i.putExtra("course_id", chosen.getCourseId());
        startActivity(i);
    }

    @Override
    public void taskComplete(String data, int id) {
        lessonList = LessonQuery.parseLessonsList(data);
        LinearLayout layout = (LinearLayout) mainLayout.findViewById(R.id.lesson_rowview);
        View.OnClickListener v = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonSelected(v.getId());
            }
        };
        Iterator<Lesson> it = lessonList.iterator();
        Lesson chosen = null;
        while (it.hasNext()) {
            Lesson next = it.next();
            if (next.getId() == lesson.getId()) {
                chosen = next;
                it.remove();
                break;
            }
        }
        ViewPopulator.populateCarouselWithSelected(lessonList, layout, R.layout.red_carousel_item,
                v, this, chosen);

        ((TextView) mainLayout.findViewById(R.id.subtitle)).setText(R.string.lessonsTitle);
        Log.w("Eduki", "Eduki: Setting main layout");
        setContentView(mainLayout);

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
