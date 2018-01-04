package eu.jnksoftware.discountfinderandroid.ui.customer.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    public List<DiscountPreferencesResponse> getDiscountPreferencesResponses() {
        return discountPreferencesResponses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_preference_list,parent,false);
    return new MyViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tags.setText(discountPreferencesResponses.get(position).getTags());
        holder.price.setText(Float.toString(discountPreferencesResponses.get(position).getPrice()));
        holder.categoryTitle.setText(discountPreferencesResponses.get(position).getCategoryTitle());
        holder.id.setText(Integer.toString(discountPreferencesResponses.get(position).getId()));
      /*  holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idpref;
                idpref=discountPreferencesResponses.get(holder.getAdapterPosition()).getId();
                deletePref(idpref,auth);
                discountPreferencesResponses.remove(holder.getAdapterPosition());
                notifyDataSetChanged();

            }
        }); */

    }
    public void removeDiscount(int position){
        discountPreferencesResponses.remove(position);
    }

    public void restoreDiscount(DiscountPreferencesResponse discount,int position){
        discountPreferencesResponses.add(position,discount);
    }
    public int getDiscountId(int position){
        return discountPreferencesResponses.get(position).getId();
    }



    @Override
    public int getItemCount() {
       return discountPreferencesResponses.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
       // Button delete;
        TextView id;
        TextView price;
        Context context;
        LinearLayout foregroundView;
        RelativeLayout backgroundView;

        TextView tags;
        TextView categoryTitle;

        public MyViewHolder(View itemView,Context context) {
            super(itemView);
            //delete=itemView.findViewById(R.id.openStoreButton);
            id=itemView.findViewById(R.id.idPref);
            price=itemView.findViewById(R.id.priceText);
            tags=itemView.findViewById(R.id.tagsTextView);
            this.context = context;
            foregroundView = itemView.findViewById(R.id.foreground_view_userpref);
            backgroundView = itemView.findViewById(R.id.background_view_userpref);
            categoryTitle=itemView.findViewById(R.id.categoryTextView);


        }

    }






}

