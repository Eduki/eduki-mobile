
package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;

import com.huskysoft.eduki.data.Course;
import com.huskysoft.eduki.data.Lesson;
import com.huskysoft.eduki.data.LessonQuery;
import com.huskysoft.eduki.data.Quiz;
import com.huskysoft.eduki.data.QuizQuery;
import com.huskysoft.eduki.data.ViewPopulator;

public class CourseActivity extends Activity implements TaskComplete {

    private static final int LESSON_ID = 0;
    private static final int QUIZZES_ID = 1;
    
    private Course course;
    private List<Lesson> lessonList;
    private List<Quiz> quizList;
    private LinearLayout mainLayout;
    private Integer taskCompleteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskCompleteCount = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String course_title = extras.getString("course_title");
            int course_id = extras.getInt("course_id");
            course = new Course(course_id, course_title);
            LessonQuery.getAllLessons(this, course_id, LESSON_ID);
            QuizQuery.getAllQuizzes(this, course_id, QUIZZES_ID);
        }
        mainLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_course, null);
        setContentView(R.layout.loading_screen);
    }

    @Override
    public void taskComplete(String data, int id) {
        if (id == LESSON_ID) {
            lessonList = LessonQuery.parseLessonsList(data);
            if (lessonList.size() == 0) {
                // TODO: Handle 0 lesson case
            } else {
                LinearLayout layout = (LinearLayout) mainLayout.findViewById(R.id.lesson_rowview);
                View.OnClickListener v = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lessonSelected(v.getId());
                    }
                };
                ViewPopulator.populateCarousel(lessonList, layout, R.layout.lesson_carousel_item, v, this);
            }
        } else if(id == QUIZZES_ID) {
            quizList = QuizQuery.parseQuizzesList(data);
            LinearLayout layout = (LinearLayout) mainLayout.findViewById(R.id.quiz_rowview);
            View.OnClickListener v = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quizSelected(v.getId());
                }
            };
            ViewPopulator.populateCarousel(quizList, layout, R.layout.quiz_carousel_item, v, this);
        }
        synchronized(taskCompleteCount) {
            taskCompleteCount++;
            if(taskCompleteCount == 2) {
                setContentView(mainLayout);
            }
        }
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
        i.putExtra("course_id", course.getId());
        startActivity(i);
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
        i.putExtra("course_id", chosen.getCourseId());
        startActivity(i);
    }
    
    /**
     * 
     * @return The current list of quizzes in the course selected
     */
    public List<Quiz> getQuizList() {
        return Collections.unmodifiableList(quizList);
    }

    /**
     * @return Copy of the list of lessons
     */
    public List<Lesson> getLessonList() {
        return Collections.unmodifiableList(lessonList);
    }
}
