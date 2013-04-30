package com.huskysoft.eduki.data;

import static com.huskysoft.eduki.data.UrlConstants.COURSES;

import java.util.List;

import com.huskysoft.eduki.TaskComplete;

public class LessonQuery {
    public static void getAllLessons(TaskComplete callback, Course course) {
    	String lessonsInCourseUrl = COURSES + "/" + course.toString();
        new ConnectionTask(callback).execute(lessonsInCourseUrl);
    }
    
    public static void getSpecificLesson(TaskComplete callback, Lesson lesson) {
    	String specificLessonUrl = COURSES + "/" + lesson.getCourse().toString() +
    							   "/" + lesson.toString();
    	new ConnectionTask(callback).execute(specificLessonUrl);
    }
    
    public static List<Lesson> parseLessonsList(String data) {
        // TODO: Implement parsing of GSON into a list of lessons,
    	// 		 associating each Lesson with their proper course and text data.
        return null;
    }
}
