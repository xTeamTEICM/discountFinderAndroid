package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStore extends AppCompatActivity {

    private String shopName;
    private int shopId;
    ShopsApiInterface apiService;
    private String auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store);
        TextView textView = findViewById(R.id.storeInfos);
        Button settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(settingsClick);
        Button delete = findViewById(R.id.deleteButton);
        delete.setOnClickListener(deleteClick);

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");
        shopName = getIntent().getStringExtra("shop");
        shopId = getIntent().getIntExtra("shopId",-1);
        textView.setText(shopName);
    }

    private final View.OnClickListener settingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Intent intent = new Intent(ViewStore.this, UpdateShop.class);
           intent.putExtra("auth",auth);
           intent.putExtra("shopId",shopId);
           startActivity(intent);
        }
    };

    private final View.OnClickListener deleteClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        deleteShop();
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
}
