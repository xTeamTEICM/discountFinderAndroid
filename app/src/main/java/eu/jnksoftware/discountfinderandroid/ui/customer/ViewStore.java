package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStore extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private RecyclerView myDiscountsRecycler;
    private ShopDiscountAdapter myDiscountsAdapter;
    private String shopName;
    private int shopId;
    ShopsApiInterface apiService;
    private String auth;
    private List<SellerDiscount> discounts = new ArrayList<>();
    private ConstraintLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store);

        layout = findViewById(R.id.viewStoreConstraintLayout);
        TextView textView = findViewById(R.id.storeInfos);
        Button settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(settingsClick);
        Button delete = findViewById(R.id.deleteButton);
        delete.setOnClickListener(deleteClick);
        Button addDiscount = findViewById(R.id.addDiscountButton);
        addDiscount.setOnClickListener(addDiscountButtonClick);
        setUpRecycler();


        apiService = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");
        shopName = getIntent().getStringExtra("shop");
        shopId = getIntent().getIntExtra("shopId",-1);
        textView.setText(shopName);

        getSellerDiscounts();

        //atach the touch helper to recycler view
        ItemTouchHelper.SimpleCallback itemTouchHelper= new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(myDiscountsRecycler);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getSellerDiscounts();
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ShopDiscountAdapter.DiscountViewHolder){
            // get the removed item name to display it in snack bar
            String desc = discounts.get(viewHolder.getAdapterPosition()).getDescription();

            // backup the removed item for undo purpose
            final SellerDiscount deletedDiscount = discounts.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            int pos = viewHolder.getAdapterPosition();
            deleteSellerDiscount(myDiscountsAdapter.getDiscountId(pos));
            myDiscountsAdapter.removeDiscount(pos);
            myDiscountsAdapter.notifyItemRemoved(pos);

            myDiscountsAdapter.notifyDataSetChanged();
            Snackbar snackbar = Snackbar
                    .make(layout, desc + " removed from your shop!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // undo is selected, restore the deleted item
                    myDiscountsAdapter.restoreDiscount(deletedDiscount, deletedIndex);
                    myDiscountsAdapter.notifyItemInserted(deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    public void deleteSellerDiscount(int id){
        Call<Void> call = apiService.deleteSellerDiscount(id,auth);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ViewStore.this, "An error occured,check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUpRecycler(){
        myDiscountsRecycler = findViewById(R.id.recyclerView2);
        myDiscountsRecycler.setLayoutManager(new LinearLayoutManager(this));
        myDiscountsRecycler.setItemAnimator(new DefaultItemAnimator());
        myDiscountsRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myDiscountsRecycler.setHasFixedSize(true);
    }

}
