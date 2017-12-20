package eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class UserPreferenceList extends AppCompatActivity {
    private User user;
   private RecyclerView recyclerView;
   private RecyclerView.LayoutManager layoutManager;
    private RecyclerPreference adapter;
    IuserService iuserService;
    List<DiscountPreferencesResponse> discountPreferencesResponses ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference_list);
        recyclerView= findViewById(R.id.userPreferenceRecycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
         Gson user = new Gson();
        this.user = user.fromJson(getIntent().getStringExtra("User"), User.class);
        Button deletePreference=findViewById(R.id.deleteprefBtn);
        iuserService = ApiUtils.getUserService();
        String auth="Bearer "+ this.user.getAccessToken();
        fetchUserPreferences(auth);

        deletePreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson user = new Gson();

                Intent intent=new Intent(UserPreferenceList.this,DeletePreference.class);
                intent.putExtra("User", user.toJson(UserPreferenceList.this.user));
                startActivity(intent);
            }
        });




       Button addPrefence = findViewById(R.id.userPreferencesBtn);
       addPrefence.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
          Gson user = new Gson();
              Intent userPreferences = new Intent(UserPreferenceList.this, UserPreferences.class);
             userPreferences.putExtra("User", user.toJson(UserPreferenceList.this.user));
           startActivity(userPreferences);
            }
           });

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
    }



    public void fetchUserPreferences(final String auth) {
        Call<List<DiscountPreferencesResponse>> disc=iuserService.getDiscountsPreference(auth);
        disc.enqueue(new Callback<List<DiscountPreferencesResponse>>() {
            @Override
            public void onResponse(Call<List<DiscountPreferencesResponse>> call, Response<List<DiscountPreferencesResponse>> response) {
                discountPreferencesResponses=response.body();
                adapter=new RecyclerPreference(discountPreferencesResponses, UserPreferenceList.this,auth);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<DiscountPreferencesResponse>> call, Throwable t) {

            }
        });

    }



    }


