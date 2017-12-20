package eu.jnksoftware.discountfinderandroid.ui.customer.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.models.token.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.ui.customer.adapters.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerShops extends AppCompatActivity {
    RecyclerView shopsRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Shop> shops = new ArrayList<>();
    ShopsApiInterface apiService;
    String auth;

    private static final int requestCode = 1;

    //rm this
    Location userLocation = new Location();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shops);
        shopsRecyclerView = findViewById(R.id.shopsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        shopsRecyclerView.setLayoutManager(layoutManager);
        shopsRecyclerView.setHasFixedSize(true);
        Gson user = new Gson();
        UserTokenResponse userTokenResponse = user.fromJson(getIntent().getStringExtra("User"),UserTokenResponse.class);
        auth = "Bearer " + userTokenResponse.getAccessToken();

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        getUserShops();
        Button addStore = findViewById(R.id.addStoreButton);
        addStore.setOnClickListener(addStoreButtonClick);
        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(refreshButtonClick);
        double lat = getIntent().getDoubleExtra("storeLat",-1);
        double lon = getIntent().getDoubleExtra("storeLon",-1);

        //got to remove this
        userLocation.setLatitude(getIntent().getDoubleExtra("lat",-1));
        userLocation.setLongitude(getIntent().getDoubleExtra("lon",-1));
}

        private final View.OnClickListener refreshButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserShops();
            }
        };

    private void getUserShops(){
        Call<List<Shop>> call = apiService.getUserShops(auth);
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response)
            {
                shops = response.body();
                adapter = new RecyclerAdapter(shops,SellerShops.this,auth);
                shopsRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toast.makeText(SellerShops.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final View.OnClickListener addStoreButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SellerShops.this,SellerAddShop.class);
            intent.putExtra("auth",auth);
            intent.putExtra("lat",userLocation.getLatitude());
            intent.putExtra("lon",userLocation.getLongitude());
            startActivityForResult(intent,requestCode);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getUserShops();
    }
}
