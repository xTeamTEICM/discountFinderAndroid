package eu.jnksoftware.discountfinderandroid.ui.customer.discount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;

public class FullDiscount extends AppCompatActivity {

    private TextView shopText;
    private TextView description;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_discount);
        String shopString="";
        String descriptionString="";
        String priceString="";
        shopText= findViewById(R.id.categoryTextView);
        description= findViewById(R.id.shortDescriptionTextView);
        price= findViewById(R.id.priceTextView);

        if(getIntent().getExtras()!=null){
            shopString = getIntent().getExtras().getString("shopName");
            descriptionString = getIntent().getExtras().getString("description");
            priceString = getIntent().getExtras().getString("price");
        }

        shopText.setText(shopString);
        description.setText(descriptionString);
        price.setText(priceString);


    }
}
