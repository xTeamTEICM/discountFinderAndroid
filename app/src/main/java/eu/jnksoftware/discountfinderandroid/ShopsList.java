package eu.jnksoftware.discountfinderandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ShopsList extends AppCompatActivity {

    private ArrayList<Shop> shopsListArray;
    private ShopFactory shopFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);

        ListView shopsList = (ListView) findViewById(R.id.shopsList);

        if (getIntent().hasExtra("shopsList")) {
            // initialize shopsListArray with a param list of shops
            //I am not sure if this works we need to check it when we have real data
            shopsListArray = (ArrayList<Shop>)getIntent().getSerializableExtra("shopsList");
        } else {
            //initialize shopsListArray with a fake list of shops
            shopFactory=new ShopFactory();
            shopFactory.sortShopList();
            shopsListArray = (ArrayList<Shop>) shopFactory.getSortedShopList();
        }

        ArrayAdapter<Shop> arrayAdapter = new ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1, shopsListArray);

        shopsList.setAdapter(arrayAdapter);
        shopsList.setOnItemClickListener(shopsItemClick());
    }

    private AdapterView.OnItemClickListener shopsItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= shopsListArray.get(position).openOnMaps();
                startActivity(intent);

            }
        };
    }
}