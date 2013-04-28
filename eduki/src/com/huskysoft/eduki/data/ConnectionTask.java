package com.huskysoft.eduki.data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.huskysoft.eduki.TaskComplete;

public class ConnectionTask extends AsyncTask<String, Void, String> {

    private TaskComplete callback;
    
    public ConnectionTask(TaskComplete callback) {
        this.callback = callback;
    }
    
    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url;
            url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = rd.readLine();
            while(line != null) {
                sb.append(line + '\n');
                line = rd.readLine();
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    protected void onPostExecute(String response) {
        callback.taskComplete(response);
    }
}
