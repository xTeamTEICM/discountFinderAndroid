package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.view.View;
import android.widget.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;

public class discountList extends AppCompatActivity {

    private ListView listViewDiscountProducts;
    private List<discountProduct> discountProducts;
    private Button buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_list);
        discountProducts=new ArrayList<discountProduct>();
        discountProducts.add(new discountProduct(R.drawable.shoe1,"Pappoutsi",BigDecimal.valueOf(100)));
        discountProducts.add(new discountProduct(R.drawable.shoe2,"Pappoutsi andriko",BigDecimal.valueOf(200)));
        this.listViewDiscountProducts=(ListView)findViewById(R.id.discountList);
        this.listViewDiscountProducts.setAdapter(new discountListAdapter(this,discountProducts));
        this.listViewDiscountProducts.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                discountProduct discountProduct=discountProducts.get(i);
                Intent intent=new Intent(discountList.this,discountListEdit.class);
                intent.putExtra("product",discountProduct);
                startActivity(intent);

                // Toast.makeText(getApplicationContext(),discountProduct.getDiscountName(),Toast.LENGTH_SHORT).show();

            }
        });




    }

}
