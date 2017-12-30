package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;

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
        return new topDiscountViewHolder(view);
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

        public topDiscountViewHolder(View itemView) {
            super(itemView);
            this.shortDesc = itemView.findViewById(R.id.topDiscountTitle);
            this.productImage = itemView.findViewById(R.id.topDiscountImage);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
