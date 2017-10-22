package eu.jnksoftware.discountfinderandroid.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Button login=(Button) findViewById(R.id.login_btn);
        login.setOnClickListener(loginClick);

        TextView registerView=(TextView) findViewById(R.id.registerTextView);
        registerView.setOnClickListener(registerViewClick);
    }

    private final View.OnClickListener loginClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
            Login.this.startActivity(new Intent(Login.this, Menu.class));
            finish();

        }
    };
    private final View.OnClickListener registerViewClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, RegisterRoleSelector.class));
        }
    };
}
