package eu.jnksoftware.discountfinderandroid.ui.customer;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.DiscountsApiInterface;
import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.Discount;
import eu.jnksoftware.discountfinderandroid.Apis.PostDiscount;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by poz on 29/11/2017.
 *
 */

public class DiscountCustomerRecyclerList extends AppCompatActivity {
    private int seekProgress = 0;
    private SeekBar distanceBar;
    private TextView distanceText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Discount> discountProducts = new ArrayList<>();
    private DiscountsApiInterface supportApi;
    private String auth;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        distanceBar = findViewById(R.id.distanceSeekBar);
        distanceText = findViewById(R.id.distanceTextView);

        setContentView(R.layout.activity_discount_customer_recycler_list);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        auth = "Bearer " + getIntent().getStringExtra("auth");
        supportApi = RestClient.getClient().create(DiscountsApiInterface.class);

        seekBarProgressCalc();
        fillDiscountProductsList();
    }

    private void fillDiscountProductsList() {
        /*Category category = new Category("1", "1");
        discountProducts.add(new Discount(1, category, "Pc", "Tech", 100, "https://www.cyberpowerpc.com/images/cs/smraidmax/blk_400.png", 500));
        adapter = new DiscountRecyclerAdapter(discountProducts);
        recyclerView.setAdapter(adapter);*/
        final PostDiscount postDiscount = new PostDiscount(41.088535, 23.551294, 1500);
        Call<List<Discount>> call = supportApi.getDiscounts(postDiscount, auth);
        call.enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                   discountProducts = response.body();
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

        distanceText.setText(seekProgress + " m");

        //TODO : make it start from 500 not from 0
        distanceBar.setMax(2000);
        distanceBar.setProgress(seekProgress);

        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekProgress = i;
                distanceText.setText(seekProgress + " m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                distanceText.setText(seekProgress + " m");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                distanceText.setText(seekProgress + " m");
            }
        });
    }
}
