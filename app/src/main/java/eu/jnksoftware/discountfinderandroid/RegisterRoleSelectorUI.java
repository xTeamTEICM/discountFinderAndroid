package eu.jnksoftware.discountfinderandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class RegisterRoleSelectorUI extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
