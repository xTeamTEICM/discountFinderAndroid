package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eu.jnksoftware.discountfinderandroid.R;

public class RegisterSeller extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        EditText firstNameField = findViewById(R.id.firstNameField);
        EditText lastNameField = findViewById(R.id.lastNameField);
        EditText eMailField = findViewById(R.id.eMailField);
        EditText passwordField = findViewById(R.id.passwordField);
        EditText brandField = findViewById(R.id.brandField);

        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(registerClick);
    }

    private final View.OnClickListener registerClick = new View.OnClickListener() {
        @Override
        public void onClick(final View r) {
            // RegisterSeller.this.startActivity(new Intent(RegisterSeller.this, MenuSeller.class));
            // finish();
        }
    };

}
