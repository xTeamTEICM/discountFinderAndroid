package eu.jnksoftware.discountfinderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class role_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);

        TextView user_register = (TextView) findViewById(R.id.tvUserRegister);
        TextView shop_register = (TextView) findViewById(R.id.tvShopRegister);

        user_register.setOnClickListener(userRegClick);
        shop_register.setOnClickListener(shopRegClick);
    }

    private View.OnClickListener userRegClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: link with user register
        }
    };

    private View.OnClickListener shopRegClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO:link with shop register
        }
    };
}
