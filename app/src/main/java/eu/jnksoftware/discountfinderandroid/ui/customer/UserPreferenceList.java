package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPreferenceList extends AppCompatActivity {
    private UserTokenResponse userTokenResponse;
   private RecyclerView recyclerView;
   private RecyclerView.LayoutManager layoutManager;
    private RecyclerPreference adapter;
    IuserService iuserService;
    List<DiscountPreferencesResponse> discountPreferencesResponses ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference_list);
        recyclerView=(RecyclerView)findViewById(R.id.userPreferenceRecycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Gson user = new Gson();
        userTokenResponse = user.fromJson(getIntent().getStringExtra("User"), UserTokenResponse.class);
        iuserService = ApiUtils.getUserService();
        String auth="Bearer "+userTokenResponse.getAccessToken();
        fetchUserPreferences(auth);



       Button addPrefence = findViewById(R.id.userPreferencesBtn);
       addPrefence.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
          Gson user = new Gson();
              Intent userPreferences = new Intent(UserPreferenceList.this, UserPreferences.class);
             userPreferences.putExtra("User", user.toJson(userTokenResponse));
           startActivity(userPreferences);
            }
           });
         }

    public void fetchUserPreferences(String auth) {
        Call<List<DiscountPreferencesResponse>> disc=iuserService.getDiscountsPreference(auth);
        disc.enqueue(new Callback<List<DiscountPreferencesResponse>>() {
            @Override
            public void onResponse(Call<List<DiscountPreferencesResponse>> call, Response<List<DiscountPreferencesResponse>> response) {
                discountPreferencesResponses=response.body();
                adapter=new RecyclerPreference(discountPreferencesResponses, UserPreferenceList.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DiscountPreferencesResponse>> call, Throwable t) {

            }
        });

    }



    }


