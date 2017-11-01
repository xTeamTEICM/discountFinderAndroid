package eu.jnksoftware.discountfinderandroid.ui.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Shop;

public class Shops extends AppCompatActivity {

    private eu.jnksoftware.discountfinderandroid.models.Shops shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);

        ListView shopsList = (ListView) findViewById(R.id.shopsList);

        if (getIntent().hasExtra("shopsList")) {
            shops = (eu.jnksoftware.discountfinderandroid.models.Shops) getIntent().getSerializableExtra("shopsList");
            ArrayAdapter<Shop> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    shops.getList()
            );

            shopsList.setAdapter(arrayAdapter);
            shopsList.setOnItemClickListener(shopsItemClick());
        }


    }

    private AdapterView.OnItemClickListener shopsItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(shops.getList().get(position).getMapsUri());
                startActivity(intent);
            }
        };
    }
}