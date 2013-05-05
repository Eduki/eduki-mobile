
package com.huskysoft.eduki.data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.huskysoft.eduki.TaskComplete;

/**
 * @author Cody Thomas Class ConnectionTask will make basic GET requests to a
 *         provided url, passing the data to a callback represented by a
 *         TaskComplete object.
 */
public class ConnectionTask extends AsyncTask<String, Void, String> {

    /**
     * The callback to pass the result to when the request is complete
     */
    private TaskComplete callback;

    /**
     * Initializes a connectionTask object
     * 
     * @param callback The object to pass the result to.
     */
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
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = rd.readLine();
            while (line != null) {
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
