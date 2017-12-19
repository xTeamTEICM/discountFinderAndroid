package eu.jnksoftware.discountfinderandroid.ui.customer.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.services.ChooseStoreLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddShop extends AppCompatActivity {
    ShopsApiInterface apiService;
    String auth;

    //rm this
    Location userLocation = new Location();
    Location storeLocation = new Location();
    private static final int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_shop);

        Button insertButton = findViewById(R.id.insertButton);
        insertButton.setOnClickListener(insertButtonClick);

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(cancelButtonClick);

        Button mapsButton = findViewById(R.id.mapsButton);
        mapsButton.setOnClickListener(mapsClick);

        EditText location = findViewById(R.id.shopLocationEditText);

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");

//        storeLocation.setLatitude(getIntent().getDoubleExtra("storeLat",-1));
//        storeLocation.setLongitude(getIntent().getDoubleExtra("storeLon",-1));

        double userLat = getIntent().getDoubleExtra("lat",-1);
        double userLon = getIntent().getDoubleExtra("lon",-1);
        userLocation.setLatitude(userLat);
        userLocation.setLongitude(userLon);

/*
        if(storeLocation.getLatitude() != -1 && storeLocation.getLongitude() != -1){
            location.setText(new Double(storeLocation.getLatitude()).toString().substring(0,5)
                    + "," + new Double(storeLocation.getLongitude()).toString().substring(0,5));
        }
        */
    }

    private final View.OnClickListener mapsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SellerAddShop.this, ChooseStoreLocation.class);
            intent.putExtra("lat",userLocation.getLatitude());
            intent.putExtra("lon",userLocation.getLongitude());
            intent.putExtra("auth",auth);
            startActivityForResult(intent,requestCode);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 & resultCode == RESULT_OK) {
            Toast.makeText(this, "Everything is ok", Toast.LENGTH_SHORT).show();
            EditText location = findViewById(R.id.shopLocationEditText);
            String lat = (Double.toString(data.getDoubleExtra("storeLat", -1)).substring(0, 5));
            String lon = (Double.toString(data.getDoubleExtra("storeLon", -1)).substring(0, 5));
            location.setText(lat + "," + lon);
        }
    }

    private void addShop(){

        String[] pos;
        EditText shopNameEditText = findViewById(R.id.shopNameEditText);
        EditText locationEditText = findViewById(R.id.shopLocationEditText);
        EditText descriptionEditText = findViewById(R.id.shopDescriptionEditText);
        pos = locationEditText.getText().toString().split(",");
        String shopName = shopNameEditText.getText().toString();
        double lon = Double.parseDouble(pos[0]);
        double lat =Double.parseDouble(pos[1]);
        PostShop postShop = new PostShop(shopName,lon,lat);
        Call<Void> call = apiService.addShop(postShop,auth);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SellerAddShop.this, "shop added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SellerAddShop.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SellerAddShop.this,SellerShops.class));
    }

    private final View.OnClickListener insertButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addShop();
        }
    };

    private final View.OnClickListener cancelButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
              SellerAddShop.this.startActivity(new Intent(SellerAddShop.this, SellerShops.class));
        }
    };

}
