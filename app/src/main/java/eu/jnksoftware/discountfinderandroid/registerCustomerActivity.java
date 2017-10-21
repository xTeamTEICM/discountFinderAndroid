package eu.jnksoftware.discountfinderandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class registerCustomerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_customer);

        Button register =(Button) findViewById(R.id.registerBtn);
        register.setOnClickListener(registerClick);

        TextView loginText=(TextView)findViewById(R.id.loginTextView);
        loginText.setOnClickListener(loginTextClick);
    }
    private View.OnClickListener loginTextClick=new View.OnClickListener(){
        @Override
        public void onClick(final View logView) {
            //ToDo:Go to Login Page

        }
    };
    private View.OnClickListener registerClick=new View.OnClickListener(){
        @Override
        public  void onClick(final View reg) {
          
            //ToDo:DO Register
        }
        };
    }

