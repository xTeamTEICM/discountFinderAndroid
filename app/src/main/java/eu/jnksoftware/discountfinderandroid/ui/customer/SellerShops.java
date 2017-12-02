package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerShops extends AppCompatActivity {
    RecyclerView shopsRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Shop> shops = new ArrayList<>();
    ShopsApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shops);
        shopsRecyclerView = findViewById(R.id.shopsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        shopsRecyclerView.setLayoutManager(layoutManager);
        shopsRecyclerView.setHasFixedSize(true);

        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        //fetchShopsList();
       // fetchShopsListWithId();
        addShop();

  /*  Button addStore = findViewById(R.id.addStoreButton);
        addStore.setOnClickListener(addStoreButtonClick);

    Button viewStore = findViewById(R.id.openStoreButton);
        viewStore.setOnClickListener(openStoreButtonClick);
    Button settingsButton = findViewById(R.id.settingsButton);
        viewStore.setOnClickListener(settingsButtonClick);
    Button deleteButton =  findViewById(R.id.deleteButton);
        viewStore.setOnClickListener(deleteButtonClick);
*/

}
    private void addShop(){

        PostShop postShop = new PostShop("gatidis",45.55548,14.12516);
        Call<List<Shop>> call = apiService.createShop(postShop);
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response)
            {
                Toast.makeText(SellerShops.this, "shop added succesfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toast.makeText(SellerShops.this, "shop add error", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void fetchShopsListWithId(){

        Call<List<Shop>> call = apiService.getShopWithId(1);
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response)
            {
                shops = response.body();
                adapter = new RecyclerAdapter(shops);
                shopsRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toast.makeText(SellerShops.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fetchShopsList(){
        Call<List<Shop>> call = apiService.getShopsList();
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response)
            {
                shops = response.body();
                Location location = new Location(45.25161,65.5151662);
                Shop shop1 = new Shop(1,1,"gatidis",location);
                Shop shop2 = new Shop(2,2,"fresh",location);

                shops.add(shop1);
                shops.add(shop2);
                adapter = new RecyclerAdapter(shops);
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
            //  SellerMainScreen.this.startActivity(new Intent(SellerMainScreen.this, SellerAddShop.class));
        }
    };

    private final View.OnClickListener openStoreButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //   SellerMainScreen.this.startActivity(new Intent(SellerMainScreen.this , viewStoreScreen.class));
        }
    };

    private final View.OnClickListener settingsButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO
        }
    };

    private final View.OnClickListener deleteButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO
        }
    };


}
