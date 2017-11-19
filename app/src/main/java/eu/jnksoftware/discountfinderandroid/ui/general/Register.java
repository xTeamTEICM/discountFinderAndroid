package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.Apis.RegisterApi;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;

public class Register extends Activity {


    private TextView firstName, lastName, email, passWord;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.registerBtn);
        firstName = (TextView) findViewById(R.id.firstNameField);
        lastName = (TextView) findViewById(R.id.lastNameField);
        email = (TextView) findViewById(R.id.eMailField);
        passWord = (TextView) findViewById(R.id.passwordField);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> params = new HashMap<>();
                params.put("firstName", firstName.getText().toString());
                params.put("lastName", lastName.getText().toString());
                params.put("eMail", email.getText().toString());
                params.put("password", passWord.getText().toString());

                JSONObject parameters = new JSONObject(params);

                RegisterApi registerApi= new RegisterApi();
                registerApi.doRegister(Register.this,parameters);


            }
        });

    }


    }

