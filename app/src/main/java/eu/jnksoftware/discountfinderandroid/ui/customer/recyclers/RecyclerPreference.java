package eu.jnksoftware.discountfinderandroid.ui.customer.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecyclerPreference extends RecyclerView.Adapter<RecyclerPreference.MyViewHolder>{
    private Context context;
    List<DiscountPreferencesResponse> preferencesList;

    public RecyclerPreference(Context context, List<DiscountPreferencesResponse> preferencesList) {
        this.context = context;
        this.preferencesList = preferencesList;
    }

    public int getPreferenceId(int position){ return preferencesList.get(position).getId();}

    public void removePreference(int position){ this.preferencesList.remove(position);}

    public void restorePreference(DiscountPreferencesResponse preference,int position){
        preferencesList.add(position,preference);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_preference_list,parent,false);
        return new RecyclerPreference.MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(RecyclerPreference.MyViewHolder holder, int position) {
        holder.category.setText(preferencesList.get(position).getCategoryTitle());
//        holder.price.setText("Τιμή " + preferencesList.get(position).getPrice());
        holder.tags.setText(preferencesList.get(position).getTags());
//        holder.id.setText(preferencesList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return preferencesList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView category,price,tags,id;
        LinearLayout foregroundView;

        public MyViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.categoryTextView);
            price = itemView.findViewById(R.id.priceText);
            tags = itemView.findViewById(R.id.tagsTextView);
            id = itemView.findViewById(R.id.idPref);
            foregroundView = itemView.findViewById(R.id.foreground_view_userpref);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

