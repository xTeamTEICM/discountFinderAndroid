package eu.jnksoftware.discountfinderandroid.ui.customer;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by poz on 29/11/2017.
 *
 */

public class DiscountCustomerRecyclerList extends AppCompatActivity{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Discount> discountProducts = new ArrayList<>();
    DiscountsApiInterface supportApi;
    private String auth;
    private GeoLocation geoLoc = new GeoLocation();


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_customer_recycler_list);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Gson user = new Gson();
        UserTokenResponse userTokenResponse = user.fromJson(getIntent().getStringExtra("user"),UserTokenResponse.class);
        auth = "Bearer " + userTokenResponse.getAccessToken();
        supportApi = RestClient.getClient().create(DiscountsApiInterface.class);
        fillDiscountProductsList();


    }

    private void fillDiscountProductsList(){
        final PostDiscount postDiscount = new PostDiscount(geoLoc.getLongitude(),geoLoc.getLatitude(),5000);
        Call<List<Discount>> call = supportApi.getDiscounts(postDiscount,auth);
        call.enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response)
            {
                Category category = new Category("1","1");
                //discountProducts = response.body();
                discountProducts.add(new Discount(1,category,"","",1,"",1));
                adapter = new DiscountRecyclerAdapter(discountProducts,getBaseContext(),auth);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {
                Toast.makeText(DiscountCustomerRecyclerList.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
