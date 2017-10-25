package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.RegisterCustomer;

public class RegisterRoleSelector extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);

        TextView user_register = findViewById(R.id.customerRegisterBtn);
        TextView shop_register = findViewById(R.id.sellerRegisterBtn);

        user_register.setOnClickListener(customerRegisterBtnClick);
        shop_register.setOnClickListener(sellerRegisterBtnClick);
    }

    private final View.OnClickListener customerRegisterBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RegisterRoleSelector.this.startActivity(new Intent(RegisterRoleSelector.this, RegisterCustomer.class));
        }
    };

    private final View.OnClickListener sellerRegisterBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // RegisterRoleSelector.this.startActivity(new Intent(RegisterRoleSelector.this, RegisterSeller.class));
        }
    };
}
