package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.adapters.TopDiscountAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuCustomer extends Fragment {

    private IuserService iuserService;
    private String auth;
    private RecyclerView topDiscountsRecycler;
    private TopDiscountAdapter topDiscountAdapter;
    private List<TopDiscount> topDiscounts;
  
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_menu_customer,container,false);
    }
  
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = getArguments().getString("auth");
        topDiscountsRecycler = view.findViewById(R.id.topDiscountsRecyclerView);
        iuserService= ApiUtils.getUserService();

// SEE WHATS WITH THIS,FROM MERGE
//         myLocation =ManageSharePrefs.readLocation("");
//         if (myLocation==null) {
//             Toast.makeText(getApplicationContext(), "No location ", Toast.LENGTH_LONG).show();
//             GeoLocation myGeoloc =new GeoLocation();
//             myLocation.setLogPos(myGeoloc.getLongitude());
//             myLocation.setLatPos(myGeoloc.getLatitude());
//         }
//         ManageSharePrefs.writeLocation(myLocation);
        setUpRecycler();
        topDiscounts = new ArrayList<>();
        getTopDiscounts();
    }

    public void setUpRecycler(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topDiscountsRecycler.setLayoutManager(manager);
        topDiscountsRecycler.setItemAnimator(new DefaultItemAnimator());
//      topDiscountsRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        topDiscountsRecycler.setHasFixedSize(true);
    }


    public void getTopDiscounts(){
        Call<List<TopDiscount>> call = iuserService.getTopDiscounts(15000,auth);
        call.enqueue(new Callback<List<TopDiscount>>() {
            @Override
            public void onResponse(Call<List<TopDiscount>> call, Response<List<TopDiscount>> response) {
                if(response.body()!=null) {
                    topDiscounts = response.body();
                    TopDiscountAdapter adapter = new TopDiscountAdapter(getContext(), topDiscounts);
                    topDiscountsRecycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<TopDiscount>> call, Throwable t) {

            }
        });
    }

}