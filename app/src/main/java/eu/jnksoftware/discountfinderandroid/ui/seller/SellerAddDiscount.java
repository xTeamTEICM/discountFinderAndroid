package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eu.jnksoftware.discountfinderandroid.R;

public class SellerAddDiscount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_discount);

        Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(insertButtonClick);

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(deleteButtonClick);
    }

    private final View.OnClickListener insertButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO
        }
    };

    private final View.OnClickListener deleteButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        //TODO SellerAddDiscount.this.startActivity(new Intent(SellerAddDiscount.this , SellerMainScreen.class));
        }
    };
}
