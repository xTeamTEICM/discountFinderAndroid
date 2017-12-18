package eu.jnksoftware.discountfinderandroid.ui.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        CheckBox sellerCheckBox = findViewById(R.id.sellerCheckBox);
        boolean isEnabled = getIntent().getBooleanExtra("isSellerEnabled",false);
        if(isEnabled){
            sellerCheckBox.setChecked(true);
        }
        else{
            sellerCheckBox.setChecked(false);
        }
    }

    private final View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent back = new Intent(Settings.this, MenuCustomer.class);
            startActivity(back);
        }
    };
}
