package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;

public class Login extends Activity {

    private String email;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Login components
        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);
        etEmail = findViewById(R.id.eMailField);

        TextView registerView = findViewById(R.id.registerBtn);
        registerView.setOnClickListener(registerBtnClick);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
            email = etEmail.getText().toString();
            performLogin();
        }
    };
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, Register.class));
        }
    };

    private void performLogin(){
        if(!email.isEmpty()) {
            Login.this.startActivity(new Intent(Login.this, MenuCustomer.class));
            finish();
        }
        else
            Toast.makeText(Login.this, "Login failed,try again", Toast.LENGTH_SHORT).show();
    }

}


