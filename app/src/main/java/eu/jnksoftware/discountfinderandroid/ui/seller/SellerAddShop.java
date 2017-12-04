package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import eu.jnksoftware.discountfinderandroid.R;

public class SellerAddShop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_shop);

        Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(insertButtonClick);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(cancelButtonClick);
    }

    private final View.OnClickListener insertButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
      //TODO
        }
    };

    private final View.OnClickListener cancelButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //  SellerAddShop.this.startActivity(new Intent(SellerAddShop.this, SellerMainScreen.class));
        }
    };

}
