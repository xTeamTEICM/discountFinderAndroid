package eu.jnksoftware.discountfinderandroid.ui.customer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Discount;

/**
 *
 * Created by poz on 29/11/2017.
 *
 */

public class DiscountRecyclerAdapter extends RecyclerView.Adapter<DiscountRecyclerAdapter.MyViewHolder>{
    public ArrayList<Discount> discountArrayList = new ArrayList<>();

    public DiscountRecyclerAdapter(ArrayList<Discount> discountList) {
        this.discountArrayList = discountList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_discount_items,parent,false);
        MyViewHolder myView = new MyViewHolder(view);
        return myView;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText("Περιγραφή :"+discountArrayList.get(position).getShortDescription());
        holder.shop.setText("Κατάστημα :"+discountArrayList.get(position).getShopName());
        holder.price.setText("Τιμή :"+String.valueOf(discountArrayList.get(position).getFinalPrice()));
        holder.image.setImageBitmap(discountArrayList.get(position).getProductImage());
    }

    @Override
    public int getItemCount() {
        return discountArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,shop,price;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recyclerHeader);
            shop = itemView.findViewById(R.id.recyclerShop);
            price = itemView.findViewById(R.id.recyclerPrice);
            image = itemView.findViewById(R.id.recyclerImage);
        }
    }
}
