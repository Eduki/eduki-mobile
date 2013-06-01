
package com.huskysoft.eduki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huskysoft.eduki.data.UserQuery;

/**
 * @author Cody Thomas LoginActivity allows a user to login to their account
 */

public class LoginActivity extends Activity implements TaskComplete {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * An onClickListener established in the xml of this activity, called when
     * the "login" button is pressed. Note: Method header must be public for the
     * xml to recognize this method.
     * 
     * @param view The view clicked. In this case, a button
     */
    public void loginClicked(View v) {
        TextView userBox = (TextView) findViewById(R.id.loginBox);
        String user = userBox.getText().toString();
        TextView passBox = (TextView) findViewById(R.id.passwordBox);
        String pass = passBox.getText().toString();
        ((ProgressBar) findViewById(R.id.progressBar1)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.loginError)).setVisibility(View.INVISIBLE);
        UserQuery.attemptLogin(user, pass, this, 0);
    }

    @Override
    public void taskComplete(String data, int id) {
        // TODO If data doesn't give me an error, store the authtoken in shared
        // preferences
        ((ProgressBar) findViewById(R.id.progressBar1)).setVisibility(View.INVISIBLE);
        boolean error = false;
        if (!error) {
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        } else {
            ((TextView) findViewById(R.id.loginError)).setVisibility(View.VISIBLE);
        }
    }
}
