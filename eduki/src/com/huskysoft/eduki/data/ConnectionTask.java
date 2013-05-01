package com.huskysoft.eduki.data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.huskysoft.eduki.TaskComplete;

public class ConnectionTask extends AsyncTask<String, Void, String> {

    private TaskComplete callback;
    
    //TODO: Delete when API is up
    private boolean DEBUG = false;
    private static int calls = -1; 
    
    public ConnectionTask(TaskComplete callback) {
        this.callback = callback;
    }
    
    @Override
    protected String doInBackground(String... urls) {
    	if (DEBUG) {
	    	String retval = "";
	    	calls++;
	    	switch (calls) {
	    		case 0:
	    			retval = 
	    				   "[" +
	    				   "{\"id\":1,\"title\":\"Bear Recipes 101\"}," +
	    				   "{\"id\":2,\"title\":\"Spicy Bear Recipes\"}," +
	    				   "{\"id\":3,\"title\":\"Soups Involving Bears\"}," +
	    				   "{\"id\":4,\"title\":\"Anatomy of Bears with regard to the culinary arts\"}]";
	    			break;
	    		case 1:
	    			retval = 
	    			       "[" +
	    			 	   "{\"id\":1,\"title\":\"Bear Recipe 1\"}," +
	    			 	   "{\"id\":2,\"title\":\"Bear Recipe 2\"}," +
	    			 	   "{\"id\":3,\"title\":\"Bear Recipe 3\"}," +
	    			 	   "{\"id\":4,\"title\":\"Bear Recipe 4\"}]";
	    			break;
	    		case 2:
	    			retval = "This is an example recipe!";
	    			break;
				default:
					break;
	    	}
			return retval;
    	} else {
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
    }
    
    @Override
    protected void onPostExecute(String response) {
        callback.taskComplete(response);
    }
}
