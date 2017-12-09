package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesResponse;

/**
 * Created by nikos on 9/12/2017.
 */

public class RecyclerPreference extends RecyclerView.Adapter<RecyclerPreference.MyViewHolder>{
    private List<DiscountPreferencesResponse> discountPreferencesResponses;
    private Context mContext;

    public RecyclerPreference(List<DiscountPreferencesResponse> discountPreferencesResponses, Context mContext) {
        this.discountPreferencesResponses = discountPreferencesResponses;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_preference_list,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tags.setText(discountPreferencesResponses.get(position).getTags());
        holder.price.setText(Integer.toString(discountPreferencesResponses.get(position).getPrice()));
        holder.categoryTitle.setText(discountPreferencesResponses.get(position).getCategoryTitle());
        holder.id.setText(Integer.toString(discountPreferencesResponses.get(position).getId()));

    }

    @Override
    public int getItemCount() {
       return discountPreferencesResponses.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView price;
        TextView tags;
        TextView categoryTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.idPref);
            price=itemView.findViewById(R.id.priceText);
            tags=itemView.findViewById(R.id.tagsTextView);
            categoryTitle=itemView.findViewById(R.id.categoryTextView);

        }
    }
}
