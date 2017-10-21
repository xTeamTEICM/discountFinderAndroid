package eu.jnksoftware.discountfinderandroid;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class loginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Button login=(Button) findViewById(R.id.login_btn);
        login.setOnClickListener(loginClick);

        TextView registerView=(TextView) findViewById(R.id.registerTextView);
        registerView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private View.OnClickListener loginClick=new View.OnClickListener(){
        @Override
        public void onClick(final View loginview) {
            //ToDo:Login to main page

        }
    };
}
