package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;

public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.registerBtn);
        register.setOnClickListener(registerBtnClick);

    }

    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public  void onClick(final View reg) {
            Register.this.startActivity(new Intent(Register.this, MenuCustomer.class));
            finish();
        }
        };
    }

