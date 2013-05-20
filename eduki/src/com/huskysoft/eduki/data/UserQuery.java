package com.huskysoft.eduki.data;

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
    public static void attemptLogin(String user, String pass, TaskComplete callback) {
        SecureConnectionTask sct = new SecureConnectionTask(callback);
        sct.setAuth(user, pass);
        //sct.execute(UrlConstants.URL_AUTH);
        // For now, assume task is fine
        callback.taskComplete("");
    }
}
