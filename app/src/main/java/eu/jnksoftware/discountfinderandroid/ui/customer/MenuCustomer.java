package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.ui.general.AboutUs;
import eu.jnksoftware.discountfinderandroid.ui.general.Settings;

public class MenuCustomer extends AppCompatActivity {

    private GeoLocation geoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_customer);

        geoLocation = new GeoLocation(this);

        if (geoLocation.canGetLocation()) {

            double latitude = geoLocation.getLatitude();
            double longitude = geoLocation.getLongitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }

        Button about = findViewById(R.id.aboutBtn);
        about.setOnClickListener(aboutClick);
        Button addDiscount = findViewById(R.id.requestDiscountBtn);
        addDiscount.setOnClickListener(settingsClick);
        Button myShops = findViewById(R.id.showShopsButton);
        myShops.setOnClickListener(showShopsButtonClick);

            File file = new File(getBaseContext().getFilesDir(), "saveFile");
            FileReader fileReader;
            String line = "";
            BufferedReader bufferedReader;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                line = bufferedReader.readLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button showSeller = findViewById(R.id.showShopsButton);
            if (line.equals("yes")) {
                showSeller.setVisibility(View.VISIBLE);
            } else {
                showSeller.setVisibility(View.GONE);
            }
    }

    private final View.OnClickListener showShopsButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuCustomer.this, SellerShops.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener shopsClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (geoLocation.getLocation() != null) {
                try {
                    // TODO : call discountAPI to take the nearest discounts
                    MenuCustomer.this.startActivity(new Intent(MenuCustomer.this, DiscountCustomerList.class));

                } catch (Exception ex) {
                    Toast.makeText(MenuCustomer.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(MenuCustomer.this, "We don't have your location yet !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener aboutClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            startActivity(new Intent(MenuCustomer.this, AboutUs.class));
        }
    };

    private final View.OnClickListener settingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = findViewById(R.id.showShopsButton);
            Intent intent = new Intent(MenuCustomer.this, Settings.class);
            intent.putExtra("isEnabled",button.isShown());
            startActivity(intent);
        }
    };

    private final void checkIfSeller() {

                /*
                showSeller.setVisibility(View.VISIBLE);
                showSeller.setVisibility(View.GONE);
                */
    }
}