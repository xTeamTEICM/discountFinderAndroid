package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.Apis.UpdateShop;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStore extends AppCompatActivity {

    private RecyclerView myDiscountsRecycler;
    private RecyclerView.Adapter myDiscountsAdapter;
    private String shopName;
    private int shopId;
    ShopsApiInterface apiService;
    private String auth;
    private List<SellerDiscount> discounts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store);

        TextView textView = findViewById(R.id.storeInfos);
        Button settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(settingsClick);
        Button delete = findViewById(R.id.deleteButton);
        delete.setOnClickListener(deleteClick);
        Button addDiscount = findViewById(R.id.addDiscountButton);
        addDiscount.setOnClickListener(addDiscountButtonClick);
        myDiscountsRecycler = findViewById(R.id.recyclerView2);
        myDiscountsRecycler.setLayoutManager(new LinearLayoutManager(this));
        myDiscountsRecycler.setHasFixedSize(true);

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");
        shopName = getIntent().getStringExtra("shop");
        shopId = getIntent().getIntExtra("shopId",-1);
        textView.setText(shopName);

        getSellerDiscounts();
    }

    private final View.OnClickListener settingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            updateShop();
        }
    };

    private final void updateShop(){
        UpdateShop updateShop = new UpdateShop(36,"ditoUpdated",45,45);
        Call<Void> call = apiService.updateShop(updateShop,auth);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ViewStore.this, "Shop updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ViewStore.this, "Shop didn't update", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final View.OnClickListener deleteClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        deleteShop();
        }
    };

    private final View.OnClickListener addDiscountButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ViewStore.this,SellerAddDiscount.class);
            intent.putExtra("auth",auth);
            startActivity(intent);
        }
    };

    private void deleteShop() {
        Call<Void> call = apiService.deleteShop(shopId, auth);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ViewStore.this, "shop deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ViewStore.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSellerDiscounts(){
        Call<List<SellerDiscount>> call = apiService.getSellerDiscounts(auth);
        call.enqueue(new Callback<List<SellerDiscount>>() {
            @Override
            public void onResponse(Call<List<SellerDiscount>> call, Response<List<SellerDiscount>> response) {
                discounts = response.body();
                Toast.makeText(ViewStore.this,response.message() + "\nLoaded",Toast.LENGTH_SHORT);
                myDiscountsAdapter = new ShopDiscountAdapter(ViewStore.this,discounts);
                myDiscountsRecycler.setAdapter(myDiscountsAdapter);
            }

            @Override
            public void onFailure(Call<List<SellerDiscount>> call, Throwable t) {
                Toast.makeText(ViewStore.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
