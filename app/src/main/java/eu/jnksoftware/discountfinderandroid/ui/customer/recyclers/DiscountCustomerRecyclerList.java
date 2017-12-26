package eu.jnksoftware.discountfinderandroid.ui.customer.recyclers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.PostDiscount;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.adapters.DiscountRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiscountCustomerRecyclerList extends AppCompatActivity {
    private int barProgress = 0;
    private SeekBar distanceBar;
    private TextView distanceText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Discount> discountProducts = new ArrayList<>();
    private IuserService supportApi;
    private User user;
    private double latitude ,longitude;
    private Location MyLocation;
    String auth;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        distanceBar = findViewById(R.id.distanceSeekBar);
        distanceText = findViewById(R.id.distanceTextView);

        setContentView(R.layout.activity_discount_customer_recycler_list);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        user= ManageSharePrefs.readUser("");
        auth=user.getTokenType()+" "+user.getAccessToken();



        supportApi = ApiUtils.getUserService();

        seekBarProgressCalc();
        fillDiscountProductsList();
    }

    private void fillDiscountProductsList() {
        /*Category category = new Category("1", "1");
        discountProducts.add(new Discount(1, category, "Pc", "Tech", 100, "https://www.cyberpowerpc.com/images/cs/smraidmax/blk_400.png", 500));
        adapter = new DiscountRecyclerAdapter(discountProducts);
        recyclerView.setAdapter(adapter);*/


        //Location for discounts : logPos = 41.088535 , latPos = 23.551294 & distanceInMeters = barProgress
        longitude = 41.088535;
        latitude = 23.551294;

        final PostDiscount postDiscount = new PostDiscount(longitude, latitude, 1500);
        Toast.makeText(DiscountCustomerRecyclerList.this, "Longitude :" + String.valueOf(longitude)  + "\n Latitude :" + String.valueOf(latitude), Toast.LENGTH_SHORT).show();
        Call<List<Discount>> call = supportApi.getDiscounts(postDiscount, auth);
        call.enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                   discountProducts = response.body();
                   //discountProducts.add(new Discount(1,"deli","eleos","me",10,"ta API",1));
                   adapter = new DiscountRecyclerAdapter(discountProducts , getBaseContext());
                   recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {
                Toast.makeText(DiscountCustomerRecyclerList.this, "Error", Toast.LENGTH_SHORT).show();
                t.getLocalizedMessage();
            }
        });
    }



    public void seekBarProgressCalc() {
        distanceBar = findViewById(R.id.distanceSeekBar);
        distanceText = findViewById(R.id.distanceTextView);

        distanceText.setText(barProgress + " m");

        //TODO : make it start from 500 not from 0
        distanceBar.setMax(2000);
        distanceBar.setProgress(barProgress);

        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                barProgress = i;
                distanceText.setText(barProgress + " m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                distanceText.setText(barProgress + " m");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                distanceText.setText(barProgress + " m");
            }
        });
    }
}
