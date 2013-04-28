package com.huskysoft.eduki.data;

import static com.huskysoft.eduki.data.UrlConstants.COURSES;

import java.util.List;

import com.huskysoft.eduki.TaskComplete;

public class CourseQuery {
    
    public static void getAllCourses(TaskComplete callback) {
        new ConnectionTask(callback).execute(COURSES);
    }
    
    public static List<Course> parseCourseList(String data) {
        // TODO: Implement parsing of GSON into a list of courses
        return null;
    }
}
