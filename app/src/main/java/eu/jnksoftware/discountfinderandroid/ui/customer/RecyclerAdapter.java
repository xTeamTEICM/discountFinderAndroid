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

/**
 * Created by dito on 12/2/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    List<Shop> arrayList = new ArrayList<>();

    public RecyclerAdapter(List<Shop> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.brandName.setText(arrayList.get(position).getBrandName());
    }

    @Override
    public int getItemCount() {
            return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView brandName;

        public MyViewHolder(View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.shopNameTextView);
        }
    }
}
