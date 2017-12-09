package eu.jnksoftware.discountfinderandroid.ui.customer;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;

public class ShopDiscountAdapter extends RecyclerView.Adapter<ShopDiscountAdapter.DiscountViewHolder> {
    Context context;
    List<SellerDiscount> discounts;

    public ShopDiscountAdapter(Context context, List<SellerDiscount> discounts) {
        this.context = context;
        this.discounts = discounts;
    }

    @Override
    public ShopDiscountAdapter.DiscountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_discount_template,parent,false);
        DiscountViewHolder discountViewHolder = new DiscountViewHolder(view);
        return discountViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopDiscountAdapter.DiscountViewHolder holder, int position) {
        holder.title.setText(discounts.get(position).getDescription());
//        holder.image.setImageBitmap(discounts.get(position).getImage());
        holder.price.setText("Τιμή " + Integer.toString((int) discounts.get(position).getCurrentPrice()));
        holder.percentage.setText("Έκπτωση " + Integer.toString((int) discounts.get(position).getDiscountPercent()) + "%");
        holder.category.setText("Κατηγορία: " + Integer.toString(discounts.get(position).getCategory()));
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public static class DiscountViewHolder extends RecyclerView.ViewHolder{
        TextView title,price,percentage,category;
        ImageView image;

        public DiscountViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvOfferTitle);
            price = itemView.findViewById(R.id.tvOfferPrice);
            percentage = itemView.findViewById(R.id.tvOfferPercentage);
            category = itemView.findViewById(R.id.tvOfferCategory);
            image = itemView.findViewById(R.id.offerItemImage);
        }
    }
}
