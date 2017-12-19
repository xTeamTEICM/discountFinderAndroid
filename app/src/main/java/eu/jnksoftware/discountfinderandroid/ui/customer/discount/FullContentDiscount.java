package eu.jnksoftware.discountfinderandroid.ui.customer.discount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;

public class FullContentDiscount extends AppCompatActivity {
    ImageView discountImage;
    TextView discountId,discountCategory,discountDescription,discountDistance,discountPrice,discountShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_content_discount);
        discountImage = findViewById(R.id.discountImage);
        discountId = findViewById(R.id.discountId);
        discountCategory = findViewById(R.id.discountCategory);
        discountDescription = findViewById(R.id.discountDescription);
        discountDistance = findViewById(R.id.discountDistance);
        discountPrice = findViewById(R.id.discountPrice);
        discountShopName = findViewById(R.id.discountShopName);

        discountImage.setImageResource(getIntent().getIntExtra("discount_image",0));
        discountId.setText("Discount Id : " + getIntent().getStringExtra("discount_id"));
        discountCategory.setText("Category : " + getIntent().getStringExtra("discount_Category"));
        discountDescription.setText("Description : " + getIntent().getStringExtra("discount_Description"));
        discountDistance.setText("Distance : " + getIntent().getStringExtra("discount_Distance") +"m");
        discountPrice.setText("Price : " + getIntent().getStringExtra("discount_Price") + "€");
        discountShopName.setText("Shop Name : " + getIntent().getStringExtra("discount_Shop_Name"));
    }
}
