package com.huskysoft.eduki.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import com.huskysoft.eduki.TaskComplete;

public class AuthConnectionTask extends AsyncTask<String, Void, String> {
    /**
     * The callback to pass the result to when the request is complete
     */
    private TaskComplete callback;
    private String user;
    private String pass;
    private int id;

    /**
     * Initializes a connectionTask object
     * 
     * @param callback The object to pass the result to.
     */
    public AuthConnectionTask(TaskComplete callback, int id) {
        this.callback = callback;
        this.id = id;
    }
    
    public void setAuth(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection conn = null;
        try {
            URL url;
            url = new URL(urls[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(user != null && pass != null) {
                Authenticator.setDefault (new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication (user, pass.toCharArray());
                    }
                });
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = rd.readLine();
            while (line != null) {
                sb.append(line + '\n');
                line = rd.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            try {
                int respCode = conn.getResponseCode();
                return "Error: " + respCode;
            } catch(Exception ex) {
                return null;
            }
        } catch (Exception e) {
            Log.e("HITTING HERE", e.toString());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        callback.taskComplete(response, id);
    }

}
