package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.DiscountPost;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddDiscount extends AppCompatActivity {
    ShopsApiInterface apiInterface;
    String auth;
    double startingPrice;
    double finalPrice;
    String description;
    int categoryId;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_discount);

        Button myDiscount = findViewById(R.id.addMyDiscountButton);
        myDiscount.setOnClickListener(myDiscountClick);

        apiInterface = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");
    }

    private View.OnClickListener myDiscountClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDiscountValues();
            addDiscount();
        }
    };

    public void addDiscount(){
        DiscountPost discountPost = new DiscountPost(categoryId,startingPrice,finalPrice,description,image);
        Call<DiscountGet> call = apiInterface.addDiscount(discountPost,auth);
        call.enqueue(new Callback<DiscountGet>() {
            @Override
            public void onResponse(Call<DiscountGet> call, Response<DiscountGet> response) {
                Toast.makeText(SellerAddDiscount.this, response.message() + "\nDiscount Added", Toast.LENGTH_SHORT).show();
                if(response.message().equals("OK")) startActivity(new Intent(SellerAddDiscount.this,ViewStore.class));
            }

            @Override
            public void onFailure(Call<DiscountGet> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    public void getDiscountValues(){
        EditText startingPrice = findViewById(R.id.etStartingPrice);
        EditText finalPrice = findViewById(R.id.etFinalPrice);
        EditText description = findViewById(R.id.etDescription);

        this.startingPrice = Double.parseDouble(startingPrice.getText().toString());
        this.finalPrice = Double.parseDouble(finalPrice.getText().toString());
        this.description = description.getText().toString();
        this.categoryId = 1;
        this.image = "img.google.gr";
    }
}
