package com.huskysoft.eduki.data;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huskysoft.eduki.TaskComplete;

/**
 * @author Cody Thomas Userquery streamlines queries to the api involving users.
 */

public class UserQuery {

    /**
     * Tries to login with the given username and password.
     * @param user The username/email
     * @param pass The password
     * @param callback The callback to be called on completion
     */
    public static void attemptLogin(String user, String pass, TaskComplete callback, int id) {
        SecureConnectionTask sct = new SecureConnectionTask(callback, id);
        sct.setAuth(user, pass);
        //sct.execute(UrlConstants.URL_AUTH);
        // For now, assume task is fine
        callback.taskComplete("", id);
    }
    
    public static void getEnrollments(TaskComplete callback, int user_id, int id) {
        String url = UrlConstants.getEnrollmentsURL(user_id);
        new ConnectionTask(callback, id).execute(url);
    }
    
    public static List<Enrollment> parseEnrollments(String data) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Enrollment>>() {
        }.getType();
        List<Enrollment> enrollments = gson.fromJson(data, collectionType);
        return enrollments;
    }
}
