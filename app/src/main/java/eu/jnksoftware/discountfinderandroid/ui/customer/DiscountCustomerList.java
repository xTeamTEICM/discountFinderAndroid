package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.DiscountCustomer;

public class DiscountCustomerList extends AppCompatActivity {


    private ListView listViewDiscountProducts;
    private List<DiscountCustomer> discountProducts;
    private Bitmap shoe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_customer_listview);

        discountProducts=new ArrayList<DiscountCustomer>();
        fillDiscountProductsList();
        this.listViewDiscountProducts=(ListView)findViewById(R.id.shopsList);
        this.listViewDiscountProducts.setAdapter(new discountListAdapter(this,discountProducts));
        this.listViewDiscountProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO
            }
        });

    }

    private void fillDiscountProductsList(){
        discountProducts.add(new DiscountCustomer(1,"Ανδρικο παπουτσι Nike"
                ,"Παπουτσής",35,shoe1));
        discountProducts.add(new DiscountCustomer(2,"Ορειβατικο Μποτακι"
                ,"Παπουτσής",50,shoe1));
        discountProducts.add(new DiscountCustomer(3,"Γυναικεία Γόβα"
                ,"Παπουτσάδικο Μένιος",89,shoe1));
        discountProducts.add(new DiscountCustomer(4,"Adidas Stan Smith"
                ,"Παπουτσάδικο Μένιος",75,shoe1));


    }
}
