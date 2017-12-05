package eu.jnksoftware.discountfinderandroid.ui.customer;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddShop extends AppCompatActivity {
    ShopsApiInterface apiService;
    String auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_shop);

        Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(insertButtonClick);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(cancelButtonClick);

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");
    }

    private void addShop(){

        String[] pos;
        EditText shopNameEditText = findViewById(R.id.shopNameEditText);
        EditText locationEditText = findViewById(R.id.shopLocationEditText);
        EditText descriptionEditText = findViewById(R.id.shopDescriptionEditText);
        pos = locationEditText.getText().toString().split(" ");
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
    private void deleteShop(int id) {
         Call<Void> call = apiService.deleteShop(id, auth);
         call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SellerAddShop.this, "shop deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SellerAddShop.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        });
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
           //   SellerAddShop.this.startActivity(new Intent(SellerAddShop.this, SellerShops.class));
            EditText delete = findViewById(R.id.shopDescriptionEditText);
            String deleteId = delete.getText().toString();
            int id = Integer.parseInt(deleteId);
            deleteShop(id);
        }
    };

}
