package eu.jnksoftware.discountfinderandroid.ui.general;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonClick);

        CheckBox seller = findViewById(R.id.sellerCheckBox);
        boolean isEnabled = getIntent().getBooleanExtra("isEnabled",false);
        if(isEnabled){
            seller.setChecked(true);
        }
        else{
            seller.setChecked(false);
        }
    }

    private final View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String filename = "saveFile";
            String isSeller;
            FileOutputStream outputStream = null;
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            CheckBox seller = findViewById(R.id.sellerCheckBox);
            if (seller.isChecked()){
                isSeller = "yes";
                try {
                    outputStream.write(isSeller.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                isSeller = "no";
                try{
                    outputStream.write(isSeller.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Intent back = new Intent(Settings.this, MenuCustomer.class);
            startActivity(back);
        }
    };

}
