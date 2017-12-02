package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.Apis.LoginApi;
import eu.jnksoftware.discountfinderandroid.R;


public class Login extends Activity {
    private String email;
    private String password;
    EditText etEmail;
    EditText etPassword;
    ProgressBar loadingBar;
    TextView loadingText;
    int loadingStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);
        etEmail = findViewById(R.id.loginEMailField);
        etPassword = findViewById(R.id.loginPasswordField);

        TextView registerView = findViewById(R.id.loginRegisterBtn);
        registerView.setOnClickListener(registerBtnClick);

        loadingText = findViewById(R.id.loadingText);
        loadingBar = findViewById(R.id.loadingBar);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {

            email = etEmail.getText().toString();
            password = etPassword.getText().toString();

            Map<String, String> loginValues = new HashMap<>();
            loginValues.put("username", email);
            loginValues.put("password", password);


            final JSONObject sendLogin = new JSONObject(loginValues);
            LoginApi loginApi = new LoginApi();
            loginApi.doLogin(Login.this, sendLogin);


            loadingBar.setVisibility(View.VISIBLE);
            loadingText.setVisibility(View.VISIBLE);
            loadingText.setText("Please Wait...");
            new aSyncTask().execute();

        }
    };

    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, Register.class));
        }
    };

    class aSyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Connection Starting ...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            for (loadingStatus=0; loadingStatus < 100; loadingStatus++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                loadingBar.setProgress(loadingStatus);
                publishProgress(loadingStatus);
            }
            if (loadingStatus == 100) {
                return "Connection Accomplished!!!";
            } else {
                return "Connection Error...";
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            loadingBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            loadingBar.setVisibility(View.INVISIBLE);
            loadingText.setText(s);
        }

    }
}


