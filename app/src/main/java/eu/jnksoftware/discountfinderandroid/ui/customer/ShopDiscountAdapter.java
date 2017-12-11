package eu.jnksoftware.discountfinderandroid.ui.customer;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        DiscountViewHolder discountViewHolder = new DiscountViewHolder(view,context);
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

    public void removeDiscount(int position){
        discounts.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreDiscount(SellerDiscount discount,int position){
        discounts.add(position,discount);
        notifyItemInserted(position);
    }

    public int getDiscountId(int position){
        return discounts.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    public static class DiscountViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        TextView title,price,percentage,category;
        ImageView image;
        Context context;
        LinearLayout foregroundView;
        RelativeLayout backgroundView;

        public DiscountViewHolder(View itemView,Context context) {
            super(itemView);
            title = itemView.findViewById(R.id.tvOfferTitle);
            price = itemView.findViewById(R.id.tvOfferPrice);
            percentage = itemView.findViewById(R.id.tvOfferPercentage);
            category = itemView.findViewById(R.id.tvOfferCategory);
            image = itemView.findViewById(R.id.offerItemImage);
            this.context = context;
            foregroundView = itemView.findViewById(R.id.foreground_view);
            backgroundView = itemView.findViewById(R.id.background_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }
}
