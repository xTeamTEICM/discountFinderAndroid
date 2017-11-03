package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;

public class SellerMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main_screen);

        List<String> shopNames = new ArrayList<>();
        shopNames.add("store 1");
        shopNames.add("store 2");
        shopNames.add("store 3");

        final Spinner spinner = (Spinner) findViewById(R.id.ShopSpinner);
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shopNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

}
