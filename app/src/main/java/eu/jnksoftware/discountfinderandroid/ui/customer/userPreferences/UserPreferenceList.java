package eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.recyclers.RecyclerPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPreferenceList extends Fragment {
    private User user;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerPreference adapter;
    private IuserService iuserService;
    private List<DiscountPreferencesResponse> discountPreferencesResponses ;
    private String auth;
    private Button addPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_preference_list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= view.findViewById(R.id.userPreferenceRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        addPreference = view.findViewById(R.id.userPreferencesBtn);
        addPreference.setOnClickListener(addPreferenceClick);

        Gson user = new Gson();
        this.user = user.fromJson(getArguments().getString("user"), User.class);
        iuserService = ApiUtils.getUserService();
        auth = getArguments().getString("auth");
        fetchUserPreferences(auth);
    }

/*
   Button updatePreference=findViewById(R.id.updateprefBtn);
   updatePreference.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Gson user = new Gson();
           Intent userUpdatePreferences = new Intent(UserPreferenceList.this, UserUpdatePreferences.class);
           userUpdatePreferences.putExtra("User", user.toJson(UserPreferenceList.this.user));
           startActivity(userUpdatePreferences);
       }
   });
   */

    private View.OnClickListener addPreferenceClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Gson user = new Gson();
            Intent userPreferences = new Intent(getContext(), UserPreferences.class);
            userPreferences.putExtra("User", user.toJson(UserPreferenceList.this.user));
            startActivity(userPreferences);
        }
    };

    public void fetchUserPreferences(final String auth) {
        Call<List<DiscountPreferencesResponse>> disc=iuserService.getDiscountsPreference(auth);
        disc.enqueue(new Callback<List<DiscountPreferencesResponse>>() {
            @Override
            public void onResponse(Call<List<DiscountPreferencesResponse>> call, Response<List<DiscountPreferencesResponse>> response) {
                discountPreferencesResponses=response.body();
                adapter=new RecyclerPreference(discountPreferencesResponses, getContext(),auth);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DiscountPreferencesResponse>> call, Throwable t) {

            }
        });
    }

    }