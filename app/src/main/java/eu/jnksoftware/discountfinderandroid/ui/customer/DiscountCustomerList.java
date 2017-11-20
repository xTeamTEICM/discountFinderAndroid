package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Discount;

public class DiscountCustomerList extends AppCompatActivity {


    private ListView listViewDiscountProducts;
    private List<Discount> discountProducts;
    private Bitmap shoe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_customer_listview);

        discountProducts= new ArrayList<>();
        fillDiscountProductsList();
        this.listViewDiscountProducts= findViewById(R.id.shopsList);
        this.listViewDiscountProducts.setAdapter(new discountListAdapter(this,discountProducts));
        this.listViewDiscountProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO
            }
        });

    }

    private void fillDiscountProductsList(){
        discountProducts.add(new Discount(1,"Ανδρικο παπουτσι Nike"
                ,"Παπουτσής",35,shoe1));
        discountProducts.add(new Discount(2,"Ορειβατικο Μποτακι"
                ,"Παπουτσής",50,shoe1));
        discountProducts.add(new Discount(3,"Γυναικεία Γόβα"
                ,"Παπουτσάδικο Μένιος",89,shoe1));
        discountProducts.add(new Discount(4,"Adidas Stan Smith"
                ,"Παπουτσάδικο Μένιος",75,shoe1));


    }
}
