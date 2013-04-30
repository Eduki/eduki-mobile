package com.huskysoft.eduki.data;

import static com.huskysoft.eduki.data.UrlConstants.COURSES;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huskysoft.eduki.TaskComplete;

public class CourseQuery {
    
    public static void getAllCourses(TaskComplete callback) {
        new ConnectionTask(callback).execute(COURSES);
    }
    
    public static List<Course> parseCourseList(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Course>>(){}.getType();
        List<Course> courses = gson.fromJson(data, collectionType);
        return courses;
    }
}
