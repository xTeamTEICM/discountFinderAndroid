package eu.jnksoftware.discountfinderandroid.ui.customer;


import java.util.*;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.*;
import android.content.*;
import android.widget.*;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Discount;


public class discountListAdapter extends  ArrayAdapter<Discount> {
    private Context context;
    private List<Discount> discountproducts;
    public discountListAdapter(Context context,List<Discount>discountproducts){
        super(context, R.layout.activity_item_discount_list,discountproducts);
        this.context=context;
        this.discountproducts=discountproducts;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.activity_item_discount_list,parent,false);
        //Set Image
        ImageView imageDiscountPhoto=view.findViewById(R.id.discountImageView);
        imageDiscountPhoto.setImageBitmap(discountproducts.get(position).getProductImage());
        //Set discountname text
        TextView textViewDiscountName=view.findViewById(R.id.discountNameTextView);
        textViewDiscountName.setText(discountproducts.get(position).getShortDescription());
        //Set discountShopName text
        TextView tvDiscountShopName = view.findViewById(R.id.discountShopName);
        tvDiscountShopName.append(discountproducts.get(position).getShopName());
        //Set discountprice text
        TextView textViewDiscountPrice=view.findViewById(R.id.discountPriceTextView);
        textViewDiscountPrice.append(String.valueOf(discountproducts.get(position).getFinalPrice()));

        return view;
    }

    public void deleteDiscount(Discount discountproduct)
    {

    }

}