package eu.jnksoftware.discountfinderandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class registerCustomerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_customer);
    }
    private View.OnClickListener login=new View.OnClickListener(){
        @Override
        public  void onClick(final View log) {
            //ToDo:DO login and welcome at main page
        }
        };
    }

