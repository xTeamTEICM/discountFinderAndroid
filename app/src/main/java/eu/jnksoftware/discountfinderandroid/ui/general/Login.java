package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

        loadingBar = findViewById(R.id.loadingBar);
        loadingText = findViewById(R.id.loadingText);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {

            email = etEmail.getText().toString();
            password = etPassword.getText().toString();

            Map<String,String>loginValues=new HashMap<>();
            loginValues.put("username",email);
            loginValues.put("password",password);


                JSONObject sendLogin = new JSONObject(loginValues);
                LoginApi loginApi = new LoginApi();
                loginApi.doLogin(Login.this, sendLogin);

                loadingBar.setVisibility(View.VISIBLE);
                loadingText.setVisibility(View.VISIBLE);}



    };
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, Register.class));
        }
    };

}


