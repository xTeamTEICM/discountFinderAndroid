package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Discount;

/**
 *
 * Created by poz on 29/11/2017.
 *
 */

public class DiscountRecyclerAdapter extends RecyclerView.Adapter<DiscountRecyclerAdapter.MyViewHolder> {
    public List<Discount> discountArrayList = new ArrayList<>();
    public Context context;
    public String auth;

    public DiscountRecyclerAdapter(List<Discount> discountList, Context context, String auth) {
        this.discountArrayList = discountList;
        this.context = context;
        this.auth = auth;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_discount_items, parent, false);
        MyViewHolder myView = new MyViewHolder(view);*/

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_discount_items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, discountArrayList, context, auth);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Bitmap errorImage = Bitmap.createBitmap(100, 100, null);
        holder.title.setText("Περιγραφή :" + discountArrayList.get(position).getShortDescription());
        holder.shop.setText("Κατάστημα :" + discountArrayList.get(position).getShopName());
        holder.price.setText("Τιμή :" + String.valueOf(discountArrayList.get(position).getFinalPrice()));
/*        try {
            holder.image.setImageBitmap(BitmapFactory.decodeStream((InputStream) new URL(discountArrayList.get(position).getProductImageUrl()).getContent()));
        } catch (Exception e) {
            holder.image.setImageBitmap(errorImage);
        }*/
    }

    @Override
    public int getItemCount() {
        return discountArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, shop, price;
        ImageView image;
        List<Discount> discountArrayList;
        Context context;
        String auth;

        public MyViewHolder(View itemView, List<Discount> discountArrayList, Context context, String auth) {
            super(itemView);
            this.discountArrayList = discountArrayList;
            this.context = context;
            this.auth = auth;
            title = itemView.findViewById(R.id.recyclerHeader);
            shop = itemView.findViewById(R.id.recyclerShop);
            price = itemView.findViewById(R.id.recyclerPrice);
            //image = itemView.findViewById(R.id.recyclerImage);
        }
    }
}
