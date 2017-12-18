package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Discount;
import eu.jnksoftware.discountfinderandroid.ui.customer.discount.FullContentDiscount;


public class DiscountRecyclerAdapter extends RecyclerView.Adapter<DiscountRecyclerAdapter.MyViewHolder> {
    private List<Discount> discountArrayList = new ArrayList<>();
    private Context context;

    public DiscountRecyclerAdapter(List<Discount> discountList , Context context) {
        this.discountArrayList = discountList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_discount_items, parent, false);
        return new MyViewHolder(view,context,discountArrayList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageUrl = "https://img.grouponcdn.com/deal/8DDtq5XRzVnLXEUnPHPd/p2-2048x1229/v1/c700x420.jpg";
        //Discount Card
        //Discount discount = discountArrayList.get(position);
        holder.title.setText("Περιγραφή :" + discountArrayList.get(position).getShortDescription());
        holder.shop.setText("Κατάστημα :" + discountArrayList.get(position).getShopName());
        holder.price.setText("Τιμή :" + String.valueOf(discountArrayList.get(position).getFinalPrice())+"€");
/*        try{
            Bitmap discountImage = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
            holder.image.setImageBitmap(discountImage);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @Override
    public int getItemCount() {
        return discountArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView title, shop, price;
        ImageView image;
        Context context;
        List<Discount> discountList = new ArrayList<>();

        public MyViewHolder(View itemView , Context context , List<Discount> discountList) {
            super(itemView);
            this.context = context;
            this.discountList = discountList;
            itemView.setOnClickListener(this);

            //Discount Card
            title = itemView.findViewById(R.id.recyclerHeader);
            shop = itemView.findViewById(R.id.recyclerShop);
            price = itemView.findViewById(R.id.recyclerPrice);
            image = itemView.findViewById(R.id.recyclerImage);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Discount discount = this.discountList.get(position);
            Intent intent = new Intent(this.context,FullContentDiscount.class);
            intent.putExtra("discount_id",String.valueOf(discount.getId()));
            intent.putExtra("discount_image",discount.getProductImageUrl());
            intent.putExtra("discount_Category",discount.getCategory());
            intent.putExtra("discount_Description",discount.getShortDescription());
            intent.putExtra("discount_Distance",String.valueOf(discount.getDistance()));
            intent.putExtra("discount_Price",String.valueOf(discount.getFinalPrice()));
            intent.putExtra("discount_Shop_Name",String.valueOf(discount.getShopName()));
            this.context.startActivity(intent);
        }
    }
}
