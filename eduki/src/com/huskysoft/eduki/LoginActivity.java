package com.huskysoft.eduki;

import android.app.Activity;
import android.view.View;

public class LoginActivity extends Activity implements TaskComplete {
    
    /**
     * An onClickListener established in the xml of this activity, called when
     * the "login" button is pressed. Note: Method header must be public
     * for the xml to recognize this method.
     * 
     * @param view The view clicked. In this case, a button
     */
    public void loginClicked(View v) {
        // TODO: Submit login attempt
    }

    @Override
    public void taskComplete(String data) {
        // TODO If data doesn't give me an error, store the authtoken in shared preferences
        
    }
}
