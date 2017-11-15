package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);

        TextView registerView = findViewById(R.id.registerBtn);
        registerView.setOnClickListener(registerBtnClick);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
            Login.this.startActivity(new Intent(Login.this, MenuCustomer.class));
            finish();

        }
    };
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, Register.class));
        }
    };
}
