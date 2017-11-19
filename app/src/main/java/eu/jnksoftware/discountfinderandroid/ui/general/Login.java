package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.Apis.loginApi;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.User;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;


public class Login extends Activity {
    private String email;
    private String password;
    public String toastLoginFailed="Login Failed,try again!";
    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Login components
        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);
        etEmail = findViewById(R.id.eMailField);
        etPassword = findViewById(R.id.passwordField);

        TextView registerView = findViewById(R.id.registerBtn);
        registerView.setOnClickListener(registerBtnClick);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
          
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            //performLogin();
              
            Map<String,String>loginValues=new HashMap<>();
            loginValues.put("username",email);
            loginValues.put("password",password);

            JSONObject sendLogin=new JSONObject(loginValues);
            loginApi loginApi=new loginApi();
            loginApi.doLogin(Login.this,sendLogin);
        }
    };
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, Register.class));
        }
    };

    private void performLogin(){
        if(email.isEmpty() || password.isEmpty())
            Toast.makeText(Login.this,toastLoginFailed, Toast.LENGTH_SHORT).show();
        else {
            Login.this.startActivity(new Intent(Login.this, MenuCustomer.class));
            finish();
        }
    }

}


