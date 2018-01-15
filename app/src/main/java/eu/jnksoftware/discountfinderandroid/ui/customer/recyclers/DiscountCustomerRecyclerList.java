package eu.jnksoftware.discountfinderandroid.ui.customer.recyclers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.adapters.DiscountRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiscountCustomerRecyclerList extends Fragment {
    private int barProgress = 500;
    private SeekBar distanceBar;
    private TextView distanceText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Discount> discountProducts = new ArrayList<>();
    private IuserService supportApi;
    String auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_discount_customer_recycler_list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        distanceBar = getView().findViewById(R.id.distanceSeekBar);
        distanceText = getView().findViewById(R.id.distanceTextView);
        recyclerView = getView().findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        auth = getArguments().getString("auth");
        supportApi = ApiUtils.getUserService();

        seekBarProgressCalc();
        fillDiscountProductsList();
    }


    private void fillDiscountProductsList() {
        Call<List<Discount>> call = supportApi.getDiscounts(barProgress, auth);
        call.enqueue(new Callback<List<Discount>>() {
            @Override
            public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                   discountProducts = response.body();
                   adapter = new DiscountRecyclerAdapter(discountProducts , getContext());
                   recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Discount>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                t.getLocalizedMessage();
            }
        });
    }

    public void seekBarProgressCalc() {
        distanceText.setText(barProgress + " m");

        //TODO : make it start from 500 not from 0
        distanceBar.setMax(2000);
        distanceBar.setProgress(barProgress);

        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                barProgress = i;
                distanceText.setText(barProgress + " m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                distanceText.setText(barProgress + " m");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                distanceText.setText(barProgress + " m");
                Call<List<Discount>> call = supportApi.getDiscounts(barProgress, auth);
                call.enqueue(new Callback<List<Discount>>() {
                    @Override
                    public void onResponse(Call<List<Discount>> call, Response<List<Discount>> response) {
                        discountProducts = response.body();
                        adapter = new DiscountRecyclerAdapter(discountProducts , getContext());
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<List<Discount>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        t.getLocalizedMessage();
                    }
                });
            }
        });
    }

}
