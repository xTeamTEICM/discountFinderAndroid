package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;

public class sellerMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main_screen);

        List<String> discountNames = new ArrayList<>();
        discountNames.add("Discount 1");
        discountNames.add("Discount 2");
        discountNames.add("Discount 3");

        final Spinner spinner = (Spinner) findViewById(R.id.ShopSpinner);
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, discountNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
