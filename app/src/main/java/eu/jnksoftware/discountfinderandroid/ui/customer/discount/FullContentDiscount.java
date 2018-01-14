package eu.jnksoftware.discountfinderandroid.ui.customer.discount;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.squareup.picasso.Picasso;

import eu.jnksoftware.discountfinderandroid.R;

public class FullContentDiscount extends AppCompatActivity {
    Context context;
    GoogleMap gmap;
    Button findDiscountBtn;
    ImageView discountImage;
    TextView discountId, discountCategory, discountDescription, discountDistance, discountPrice, discountShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_content_discount);

        findDiscountBtn = findViewById(R.id.findDiscount);
        //findDiscountBtn.setOnClickListener(findDiscountBtnClick);

        discountImage = findViewById(R.id.discountImage);
        discountId = findViewById(R.id.discountId);
        discountCategory = findViewById(R.id.discountCategory);
        discountDescription = findViewById(R.id.discountDescription);
        discountDistance = findViewById(R.id.discountDistance);
        discountPrice = findViewById(R.id.discountPrice);
        discountShopName = findViewById(R.id.discountShopName);

        Picasso.with(context).load(getIntent().getStringExtra("discount_image")).noPlaceholder().fit().into(discountImage);
        discountId.setText("Discount Id : " + getIntent().getStringExtra("discount_id"));
        discountCategory.setText("Category : " + getIntent().getStringExtra("discount_Category"));
        discountDescription.setText("Description : " + getIntent().getStringExtra("discount_Description"));
        discountDistance.setText("Distance : " + getIntent().getStringExtra("discount_Distance") + "m");
        discountPrice.setText("Price : " + getIntent().getStringExtra("discount_Price") + "€");
        discountShopName.setText("Shop Name : " + getIntent().getStringExtra("discount_Shop_Name"));


        findDiscountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String log = getIntent().getStringExtra("discount_Log");
                        String lat = getIntent().getStringExtra("discount_Lat");
                        String name = getIntent().getStringExtra("discount_Shop_Name");
                        String uri = "geo: "+lat+","+log+"?q=("+name+")@"+lat+","+log;
                        Uri uriIntent = Uri.parse(uri);
                        Intent mapLocation = new Intent(Intent.ACTION_VIEW, uriIntent);
                        mapLocation.setPackage("com.google.android.apps.maps");
                        startActivity(mapLocation);
                    }
                },500);
            }
        });
    }

/*    private final View.OnClickListener findDiscountBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View findDiscountView) {

            String log = getIntent().getStringExtra("discount_Log");
            String lat = getIntent().getStringExtra("discount_Lat");
            mapLocation.putExtra("log",log);
            mapLocation.putExtra("lat",lat);
            startActivity(mapLocation);
        }
    };*/
}
