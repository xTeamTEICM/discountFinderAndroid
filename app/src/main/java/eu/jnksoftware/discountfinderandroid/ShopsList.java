package eu.jnksoftware.discountfinderandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class ShopsList extends AppCompatActivity {

    private ArrayList<String> shopsListArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);

        ListView shopsList = (ListView) findViewById(R.id.shopsList);

        if (getIntent().hasExtra("shopsList")) {
            // initialize shopsListArray with a param list of shops
            shopsListArray = getIntent().getStringArrayListExtra("shopsList");
        } else {
            //initialize shopsListArray with a fake list of shops
            shopsListArray = new ArrayList<>(asList("Adidas", "Sports", "Nike Sports", "Admiral"));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shopsListArray);

        shopsList.setAdapter(arrayAdapter);
    }
}
