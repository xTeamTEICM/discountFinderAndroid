package eu.jnksoftware.discountfinderandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class activity_register_seller extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_seller);

        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etSurname = (EditText) findViewById(R.id.etSurname);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPass = (EditText) findViewById(R.id.etPassword);
        EditText etBrand = (EditText) findViewById(R.id.etBrand);

        Button register = (Button) findViewById(R.id.btRegister);
        register.setOnClickListener(registerClick);
    }

    private View.OnClickListener registerClick = new View.OnClickListener(){
        @Override
        public void onClick(final View r){
            //TODO: Perform Register
        }
    };

}
