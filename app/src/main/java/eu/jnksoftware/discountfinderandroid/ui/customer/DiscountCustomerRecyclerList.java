package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Discount;

/**
 *
 * Created by poz on 29/11/2017.
 *
 */

public class DiscountCustomerRecyclerList extends AppCompatActivity{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<Discount> discountProducts = new ArrayList<>();
    private Bitmap shoe1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_customer_recycler_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        fillDiscountProductsList();
        adapter = new DiscountRecyclerAdapter(discountProducts);
        recyclerView.setAdapter(adapter);
    }

    private void fillDiscountProductsList(){
        discountProducts.add(new Discount(1,"Ανδρικο παπουτσι Nike"
                ,"Παπουτσής",35,shoe1));
        discountProducts.add(new Discount(2,"Ορειβατικο Μποτακι"
                ,"Παπουτσής",50,shoe1));
        discountProducts.add(new Discount(3,"Γυναικεία Γόβα"
                ,"Ο Μένιος",89,shoe1));
        discountProducts.add(new Discount(4,"Adidas Stan Smith"
                ,"Ο Μένιος",75,shoe1));
    }
}
