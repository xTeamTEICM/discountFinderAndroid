package eu.jnksoftware.discountfinderandroid.ui.general;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;

public class Settings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonClick);

    }

    private final View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CheckBox seller = findViewById(R.id.sellerCheckBox);
            boolean isSellerChecked = seller.isChecked();
            Intent back = new Intent(Settings.this,MenuCustomer.class);
            back.putExtra("checkBoxValue",isSellerChecked);
            startActivity(back);
        }
    };


}
