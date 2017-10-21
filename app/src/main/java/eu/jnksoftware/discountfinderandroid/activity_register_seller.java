package eu.jnksoftware.discountfinderandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class activity_register_seller extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_seller);
    }
}
