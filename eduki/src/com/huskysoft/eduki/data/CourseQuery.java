
package com.huskysoft.eduki.data;

import static com.huskysoft.eduki.data.UrlConstants.COURSES;

import android.util.DisplayMetrics;
import android.view.View;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huskysoft.eduki.TaskComplete;

/**
 * @author Cody Thomas CourseQuery will make queries to the api
 */
public class CourseQuery {

    /**
     * Will get a list of all courses, returned to the callback as a string
     * 
     * @param callback The callback for when the request is complete
     */
    public static void getAllCourses(TaskComplete callback, int id) {
        new ConnectionTask(callback, id).execute(COURSES);
    }

    /**
     * Will get a list of all courses, returned to the callback as a string
     * 
     * @param callback The callback for when the request is complete
     */
    public static void getAllUserCourses(TaskComplete callback, int id, int user_id) {
        new ConnectionTask(callback, id).execute(UrlConstants.getUserCoursesURL(user_id));
    }

    /**
     * Will get a list of one courses, returned to the callback as a string
     * 
     * @param callback The callback for when the request is complete
     */
    public static void getCourse(TaskComplete callback, int id, int course_id) {
        new ConnectionTask(callback, id).execute(UrlConstants.getSingleCourseURL(course_id));
    }

    /**
     * Will parse the string and return a list of courses represented in the
     * string.
     * 
     * @param data The json string to be parsed
     * @return A list of courses represented in the string
     */
    public static List<Course> parseCourseList(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Course>>() {
        }.getType();
        List<Course> courses = gson.fromJson(data, collectionType);
        return courses;
    }

    /**
     * Parses data and returns the result
     * 
     * @param data The json string to be parsed
     * @return The course represented in data
     */
    public static Course parseCourse(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, Course.class);
    }
}
