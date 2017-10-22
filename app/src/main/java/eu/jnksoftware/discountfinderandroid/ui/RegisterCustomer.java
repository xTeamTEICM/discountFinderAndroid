package eu.jnksoftware.discountfinderandroid.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import eu.jnksoftware.discountfinderandroid.R;

public class RegisterCustomer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_customer);

        Button register = (Button) findViewById(R.id.registerBtn);
        register.setOnClickListener(registerClick);

    }

    private final View.OnClickListener registerClick = new View.OnClickListener() {
        @Override
        public  void onClick(final View reg) {
            RegisterCustomer.this.startActivity(new Intent(RegisterCustomer.this, Menu.class));
            finish();
        }
        };
    }

