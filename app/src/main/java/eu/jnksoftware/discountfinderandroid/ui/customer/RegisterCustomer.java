package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eu.jnksoftware.discountfinderandroid.R;

public class RegisterCustomer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        Button register = findViewById(R.id.registerBtn);
        register.setOnClickListener(registerBtnClick);

    }

    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public  void onClick(final View reg) {
            RegisterCustomer.this.startActivity(new Intent(RegisterCustomer.this, MenuCustomer.class));
            finish();
        }
        };
    }

