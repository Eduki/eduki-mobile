package com.huskysoft.eduki.data;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        AuthConnectionTask sct = new AuthConnectionTask(callback, id);
        sct.setAuth(user, pass);
        sct.execute(UrlConstants.URL_AUTH);
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
    
    public static User parseLoginAttempt(String data) {
        Map<String, String> fromJson = null;
        if(data.startsWith("Error")) {
            return null;
        } else {
            fromJson = new Gson().fromJson(data, new TypeToken<HashMap<String, String>>() {}.getType());
            if(fromJson.containsKey("success") && fromJson.get("success").equals("false"))
                return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(data, User.class);
        
    }
}
