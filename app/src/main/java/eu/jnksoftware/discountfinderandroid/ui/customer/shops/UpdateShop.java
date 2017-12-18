package eu.jnksoftware.discountfinderandroid.ui.customer.shops;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.Apis.UpdatePostShop;
import eu.jnksoftware.discountfinderandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateShop extends AppCompatActivity {

    ShopsApiInterface apiService;
    String auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shop);

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");

        Button update = findViewById(R.id.updateButton);
        update.setOnClickListener(updateClickListener);
        Button cancel = findViewById(R.id.cancelUpdateButton);
        cancel.setOnClickListener(cancelUpdateClickListener);
    }

    private final View.OnClickListener updateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText name = findViewById(R.id.updateShopNameEditText);
            EditText location = findViewById(R.id.updateShopLocationEditText);
            EditText description = findViewById(R.id.updateShopDescriptionEditText);
            String[] pos;
            pos = location.getText().toString().split(" ");
            String shopName = name.getText().toString();
            String shopDesciption = description.getText().toString();
            double lat =Double.parseDouble(pos[0]);
            double lon = Double.parseDouble(pos[1]);
            updateShop(shopName,lat,lon,shopDesciption);
        }
    };

    private final View.OnClickListener cancelUpdateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(UpdateShop.this,SellerShops.class));
        }
    };

    private final void updateShop(String brandName,double lat,double lon, String shopDescription){
        int id = getIntent().getIntExtra("shopId",-1);
        UpdatePostShop updatePostShop = new UpdatePostShop(id,brandName,lat,lon);
        Call<Void> call = apiService.updateShop(updatePostShop,auth);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateShop.this, "Shop updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateShop.this, "Shop didn't update", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
