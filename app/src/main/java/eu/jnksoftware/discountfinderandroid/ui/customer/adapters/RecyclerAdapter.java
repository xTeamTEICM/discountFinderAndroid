package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.ui.customer.shops.ViewStore;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Shop shop);
    }

    private List<Shop> shopList = new ArrayList<>();
    private Context context;
    private String auth;

    public RecyclerAdapter(List<Shop> shopList,Context context,String auth){
        this.shopList = shopList;
        this.context = context;
        this.auth = auth;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view,context, shopList,auth);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    //    holder.bind(shopList.get(position), shopListener);
        holder.brandName.setText(shopList.get(position).getBrandName());
    }


    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView brandName;
        List<Shop> shops = new ArrayList<>();
        Context context;
        String auth;

        public MyViewHolder(View itemView, Context context, List<Shop> shops, String auth) {
            super(itemView);
             //DiscountRequestApi_Retrofit
//            brandName = itemView.findViewById(R.id.categoryTextView);
//
            this.shops = shops;
            this.context = context;
            this.auth = auth;
            itemView.setOnClickListener(this);
            brandName = itemView.findViewById(R.id.shopNameTextView);
// dev
        }
/*
        public void bind(final Shop shop, final OnItemClickListener listener) {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(shop);
                }
            });
        }
*/
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Shop shop = this.shops.get(position);
            Intent intent = new Intent(context,ViewStore.class);
            intent.putExtra("shop",shop.getBrandName());
            intent.putExtra("shopId",shop.getId());
            intent.putExtra("auth",auth);
            this.context.startActivity(intent);
        }
    }
}
