package eu.jnksoftware.discountfinderandroid.ui.customer.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.RetrofitClient;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.services.ChooseStoreLocation;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddShop extends AppCompatActivity {
    private IuserService apiService;
    private String auth;
    private Location userLocation = new Location();
    private Location storeLocation = new Location();
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

        apiService = RetrofitClient.getClient(ApiUtils.getBaseUrl()).create(IuserService.class);
        apiService = ApiUtils.getUserService();
        auth = getIntent().getStringExtra("auth");

        double userLat = getIntent().getDoubleExtra("lat",-1);
        double userLon = getIntent().getDoubleExtra("lon",-1);
        userLocation.setLatPos(userLat);
        userLocation.setLogPos(userLon);

    }

    private final View.OnClickListener mapsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SellerAddShop.this, ChooseStoreLocation.class);
            intent.putExtra("lat",userLocation.getLatPos());
            intent.putExtra("lon",userLocation.getLogPos());
            intent.putExtra("auth",auth);
            startActivityForResult(intent,requestCode);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 & resultCode == RESULT_OK) {
            storeLocation.setLatPos(data.getDoubleExtra("storeLat", -1));
            storeLocation.setLogPos(data.getDoubleExtra("storeLon", -1));
            TextView location = findViewById(R.id.shopMapsLocationTextView);
            location.setText(data.getStringExtra("streetName"));
            TextView desctiprion = findViewById(R.id.shopCityEditText);
            desctiprion.setText(data.getStringExtra("cityName"));
        }
    }

    private void addShop(){

        EditText shopNameEditText = findViewById(R.id.shopNameEditText);
        String shopName = shopNameEditText.getText().toString();
        PostShop postShop = new PostShop(shopName,storeLocation.getLatPos(),storeLocation.getLogPos());
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
        finish();
    }

    private final View.OnClickListener insertButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addShop();
            finish();
        }
    };

    private final View.OnClickListener cancelButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
