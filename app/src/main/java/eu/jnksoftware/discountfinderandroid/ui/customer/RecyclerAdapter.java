package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Shop;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Shop shop);
    }

    private List<Shop> shopList = new ArrayList<>();
    private final OnItemClickListener shopListener;
    public RecyclerAdapter(List<Shop> shopList, OnItemClickListener shopListener){
        this.shopList = shopList;
        this.shopListener = shopListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.bind(shopList.get(position), shopListener);
        holder.brandName.setText(shopList.get(position).getBrandName());
    }


    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brandName;

        public MyViewHolder(View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.shopNameTextView);
        }

        public void bind(final Shop shop, final OnItemClickListener listener) {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(shop);
                }
            });
        }
    }
}
