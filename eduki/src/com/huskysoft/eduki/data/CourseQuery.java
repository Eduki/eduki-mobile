
package com.huskysoft.eduki.data;

import static com.huskysoft.eduki.data.UrlConstants.COURSES;

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
    public static void getAllCourses(TaskComplete callback) {
        new ConnectionTask(callback).execute(COURSES);
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
}
