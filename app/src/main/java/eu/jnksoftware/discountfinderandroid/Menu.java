package eu.jnksoftware.discountfinderandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        Button shops = (Button) findViewById(R.id.showShopsBtn);
        shops.setOnClickListener(shopsClick);
        Button about = (Button) findViewById(R.id.aboutBtn);
        about.setOnClickListener(aboutClick);

    }

    private View.OnClickListener shopsClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            // ToDo : Create Shops Intent and Start It
        }
    };

    private View.OnClickListener aboutClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            // ToDo : Create Menu Intent and Start It
        }
    };

}
