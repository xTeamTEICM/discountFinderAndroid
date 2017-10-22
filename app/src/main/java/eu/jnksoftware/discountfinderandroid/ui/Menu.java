package eu.jnksoftware.discountfinderandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.algorithms.ShopSort;
import eu.jnksoftware.discountfinderandroid.api.APIConfig;
import eu.jnksoftware.discountfinderandroid.api.ShopsAPI;
import eu.jnksoftware.discountfinderandroid.models.Position;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.services.GeoLocationService;

public class Menu extends AppCompatActivity {

    private GeoLocationService geoLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        geoLocationService = new GeoLocationService(this);

        if (geoLocationService.canGetLocation()) {

            double latitude = geoLocationService.getLatitude();
            double longitude = geoLocationService.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }

        Button shops = (Button) findViewById(R.id.showShopsBtn);
        shops.setOnClickListener(shopsClick);
        Button about = (Button) findViewById(R.id.aboutBtn);
        about.setOnClickListener(aboutClick);

    }

    private final View.OnClickListener shopsClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (geoLocationService.getLocation() != null) {
                List<Shop> shops = new ArrayList<>();
                try {
                    ShopsAPI shopsAPI = new ShopsAPI(APIConfig.APILinkReal + "shop/");
                    shops = shopsAPI.getList();
                    ShopSort shopSort = new ShopSort(
                            shops,
                            new Position(
                                    geoLocationService.getLatitude(),
                                    geoLocationService.getLongitude()
                            )
                    );
                    shopSort.sortShopList();
                    shops = shopSort.getSortedShopList();
                } catch (Exception ex) {
                    Toast.makeText(Menu.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ShopSort shopSort = new ShopSort();
                    shopSort.sortShopList();
                    shops = shopSort.getSortedShopList();
                } finally {
                    Intent intent = new Intent(Menu.this, Shops.class);
                    intent.putExtra("shopsList", new ArrayList<>(shops));
                    startActivity(intent);
                }
            } else {
                Toast.makeText(Menu.this, "We don't have your location yet !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener aboutClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            startActivity(new Intent(Menu.this, AboutUs.class));
        }
    };


}
