package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;
import eu.jnksoftware.discountfinderandroid.ui.customer.discount.FullContentDiscount;

public class TopDiscountAdapter extends RecyclerView.Adapter<TopDiscountAdapter.topDiscountViewHolder>{
    private Context context;
    private List<TopDiscount> topDiscountList;

    public TopDiscountAdapter(Context context, List<TopDiscount> topDiscountList) {
        this.context = context;
        this.topDiscountList = topDiscountList;
    }

    public List<TopDiscount> getTopDiscountList(){
        return topDiscountList;
    }

    @Override
    public topDiscountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_discount_element,parent,false);
        return new topDiscountViewHolder(view,context,topDiscountList);
    }

    @Override
    public void onBindViewHolder(topDiscountViewHolder holder, int position) {
        holder.shortDesc.setText(topDiscountList.get(position).getShortDescription());
        Picasso.with(context).load(topDiscountList.get(position).getProductImage()).noPlaceholder().fit().into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return topDiscountList.size();
    }


    public static class topDiscountViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private TextView shortDesc;
        private ImageView productImage;
        List<TopDiscount> discountList = new ArrayList<>();
        Context context;

        public topDiscountViewHolder(View itemView,Context context,List<TopDiscount> discounts) {
            super(itemView);
            this.context = context;
            this.discountList = discounts;
            this.shortDesc = itemView.findViewById(R.id.topDiscountTitle);
            this.productImage = itemView.findViewById(R.id.topDiscountImage);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            TopDiscount topDiscount = discountList.get(position);
            Intent intent = new Intent(this.context,FullContentDiscount.class);
            intent.putExtra("discount_image",topDiscount.getProductImageURL());
            intent.putExtra("discount_Category",topDiscount.getCategory());
            intent.putExtra("discount_Description",topDiscount.getShortDescription());
            intent.putExtra("discount_Price",String.valueOf(topDiscount.getFinalPrice()));
            intent.putExtra("discount_Shop_Name",String.valueOf(topDiscount.getShopName()));
            this.context.startActivity(intent);
        }
    }

}
