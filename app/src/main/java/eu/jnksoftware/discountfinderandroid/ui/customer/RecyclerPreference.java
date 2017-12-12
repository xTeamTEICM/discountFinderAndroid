package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nikos on 9/12/2017.
 */

public class RecyclerPreference extends RecyclerView.Adapter<RecyclerPreference.MyViewHolder>{
    private List<DiscountPreferencesResponse> discountPreferencesResponses;
    private Context mContext;
    private String auth;
    private IuserService iuserService;
    private RecyclerPreference recyclerPreference;


    public RecyclerPreference(List<DiscountPreferencesResponse> discountPreferencesResponses, Context mContext,String auth) {
        this.discountPreferencesResponses = discountPreferencesResponses;
        this.mContext = mContext;
        this.auth=auth;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_preference_list,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tags.setText(discountPreferencesResponses.get(position).getTags());
        holder.price.setText(Integer.toString(discountPreferencesResponses.get(position).getPrice()));
        holder.categoryTitle.setText(discountPreferencesResponses.get(position).getCategoryTitle());
        holder.id.setText(Integer.toString(discountPreferencesResponses.get(position).getId()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idpref;
                idpref=discountPreferencesResponses.get(position).getId();
                deletePref(idpref,auth);




            }
        });

    }

    @Override
    public int getItemCount() {
       return discountPreferencesResponses.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Button delete;
        TextView id;
        TextView price;
        TextView tags;
        TextView categoryTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            delete=itemView.findViewById(R.id.openStoreButton);
            id=itemView.findViewById(R.id.idPref);
            price=itemView.findViewById(R.id.priceText);
            tags=itemView.findViewById(R.id.tagsTextView);
            categoryTitle=itemView.findViewById(R.id.categoryTextView);


        }

    }

    public void deletePref(final int id, String auth)
    {   iuserService= ApiUtils.getUserService();
        Call<Void> delete=iuserService.deleteDiscountPreference(id,auth);
        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(mContext,"You Preference with "+id+" remove Succesfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}

