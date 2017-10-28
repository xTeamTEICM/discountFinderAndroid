package eu.jnksoftware.discountfinderandroid.ui.general;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.view.View;
import android.widget.*;
import eu.jnksoftware.discountfinderandroid.R;

public class deleteDiscountEdit extends AppCompatActivity {
    private  Button buttonDelete;
    private Button buttonBack;
    private TextView discName;
    private TextView discPrice;
    private ImageView discImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_discount_edit);
        Intent intent =getIntent();
        discountProduct discountproduct= (discountProduct) intent.getSerializableExtra("product");
        this.buttonBack= (Button) findViewById(R.id.backBtn);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(deleteDiscountEdit.this,deleteDiscount.class);
                startActivity(intent);

            }
        });
        this.discName= (TextView) findViewById(R.id.textViewName);
        this.discName.setText(discountproduct.getDiscountName());
        this.discPrice= (TextView) findViewById(R.id.textViewPrice);
        this.discPrice.setText(String.valueOf(discountproduct.getDiscountPrice()));
        this.discImage= (ImageView) findViewById(R.id.imageDiscountView);
        this.discImage.setImageResource(discountproduct.getDiscountImage());
    }
}
