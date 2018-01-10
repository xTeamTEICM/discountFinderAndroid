package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.HttpCall;
import eu.jnksoftware.discountfinderandroid.CustomerMenu;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends Activity {
    ProgressBar loadingBar;
    TextView loadingText;
    int loadingStatus = 0;
    private EditText eMail;
    private EditText password;
    IuserService iuserService;
    private String username;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eMail = findViewById(R.id.loginEMailField);
        password = findViewById(R.id.loginPasswordField);
        eMail.setText("user@jnksoftware.eu");
        password.setText("myPassword");
        iuserService = ApiUtils.getUserService();

        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);

        TextView registerView = findViewById(R.id.loginRegisterBtn);
        registerView.setOnClickListener(registerBtnClick);

        loadingText = findViewById(R.id.loadingText);
        loadingBar = findViewById(R.id.loadingBar);
        ManageSharePrefs.init(getApplicationContext());



        User userFromPrefs = ManageSharePrefs.readUser(null);
        if (userFromPrefs != null) {
            Toast.makeText(Login.this, "wowooww" + userFromPrefs.getTokenType(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Login.this, "nothing", Toast.LENGTH_SHORT).show();
        }


    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
            int response;

            username=eMail.getText().toString();
            pass=password.getText().toString();

           /* userTokenRequest.setUsername(eMail.getText().toString().trim());
            userTokenRequest.setPassword(password.getText().toString().trim());*/
            doLogin(username,pass);
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


    public void doLogin(String username,String password) {


        Call<User> call = iuserService.login(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {

                    User userTokenResponse = response.body();

                    Intent menuCustomer = new Intent(Login.this, CustomerMenu.class);
                    menuCustomer.putExtra("email",eMail.getText().toString());

                    String fcmTokenDataString = ManageSharePrefs.readFcmTokenData("");

                    if (userTokenResponse.getAccessToken().equals(fcmTokenDataString)) {
                        startActivity(menuCustomer);
                    } else {
                        ManageSharePrefs.writeUser(userTokenResponse);
                        FcmToken token = new FcmToken(FirebaseInstanceId.getInstance().getToken());
                        HttpCall httpCall = new HttpCall();
                        int statusCode;
                        statusCode = httpCall.setFcmToken(token, userTokenResponse.getTokenType() + " " + userTokenResponse.getAccessToken());
                        if (statusCode == 200) {
                            ManageSharePrefs.writeFcmTokenData(userTokenResponse.getAccessToken());
                        }
                        startActivity(menuCustomer);
                    }
                } else {
                    Toast.makeText(Login.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("MainActivity", "onFailure" + t.getMessage());
                Toast.makeText(Login.this, "Wrong!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private class aSyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Connection Starting ...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            for (loadingStatus = 0; loadingStatus < 50; loadingStatus++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                loadingBar.setProgress(loadingStatus);
                publishProgress(loadingStatus);
            }
            if (loadingStatus == 50) {
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
            loadingText.setVisibility(View.INVISIBLE);
        }

    }

}

