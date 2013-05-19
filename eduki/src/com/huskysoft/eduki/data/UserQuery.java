package com.huskysoft.eduki.data;

import com.huskysoft.eduki.TaskComplete;

public class UserQuery {

    public static void attemptLogin(String user, String pass, TaskComplete callback) {
        SecureConnectionTask sct = new SecureConnectionTask(callback);
        sct.setAuth(user, pass);
        //sct.execute(UrlConstants.URL_AUTH);
        // For now, assume task is fine
        callback.taskComplete("");
    }
}
