package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.Apis.loginApi;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.User;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;



public class Login extends Activity {
    private EditText eMail;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         eMail=(EditText) findViewById(R.id.eMailField);
         password=(EditText)findViewById(R.id.passwordField);



        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);

        TextView registerView = findViewById(R.id.registerBtn);
        registerView.setOnClickListener(registerBtnClick);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
            Map<String,String>loginValues=new HashMap<>();
            loginValues.put("username",eMail.getText().toString());
            loginValues.put("password",password.getText().toString());
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
}
