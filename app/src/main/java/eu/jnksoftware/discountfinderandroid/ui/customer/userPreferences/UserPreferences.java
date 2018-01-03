package eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPreferences extends AppCompatActivity {

    private User user;
    private int seekBarProgress = 0;
    private TextView showSeekProgress;
    private List<Category> categories = new ArrayList<>();
    private List<String> catTemp = new ArrayList<>();
    IuserService iuserService;
    String accessToken;
    private ArrayAdapter<String> spinContentAdapter;
    private Spinner spinnerCat;
    private String auth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);
        iuserService= ApiUtils.getUserService();
        user = ManageSharePrefs.readUser(null);


        
        spinnerCat = findViewById(R.id.spinnerCategory);
        spinContentAdapter = new ArrayAdapter<>(UserPreferences.this,android.R.layout.simple_list_item_1, catTemp);
        spinnerCat.getBackground().setAlpha(130);
        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);

        fetchCategories();

        SeekBar seekBarPrice = findViewById(R.id.seekBarPrice);
        showSeekProgress =  findViewById(R.id.tvSeekBarValue);
        Button savePrefButton = findViewById(R.id.btSavePreferences);
        savePrefButton.setOnClickListener(savePrefClick);

        showSeekProgress.setText("Μέχρι "+seekBarProgress + " ευρώ");
        seekBarPrice.setMax(500);
        seekBarPrice.setProgress(seekBarProgress);
        seekBarPrice.getBackground().setAlpha(200);

        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress = i;
                showSeekProgress.setText("Μέχρι " + seekBarProgress + " ευρώ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private final View.OnClickListener savePrefClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
            discountPreferencesRequest.setCategory(String.valueOf(categories.get((int) spinnerCat.getSelectedItemId()).getId()));
            discountPreferencesRequest.setPrice(String.valueOf(seekBarProgress));
            discountPreferencesRequest.setTags("Sample");


            doUserPreference(discountPreferencesRequest);
            Toast.makeText(UserPreferences.this, discountPreferencesRequest.getCategory(), Toast.LENGTH_SHORT).show();
        }
    };

    public void doUserPreference(final DiscountPreferencesRequest discountPreferencesRequest) {
        auth="Bearer "+user.getAccessToken();
        Call<DiscountPreferencesPostResponse> call = iuserService.postDiscountPreferences(discountPreferencesRequest,auth);
            call.enqueue(new Callback<DiscountPreferencesPostResponse>() {
                @Override
                public void onResponse(Call<DiscountPreferencesPostResponse> call, Response<DiscountPreferencesPostResponse> response) {
                if(response.isSuccessful()){
                    int statusCode=response.code();
                    Log.d("UserPreferences","onResponse:"+statusCode);
                    DiscountPreferencesPostResponse discountPreferencesPostResponse=response.body();
                    Toast.makeText(UserPreferences.this,"Preference add "+discountPreferencesPostResponse,Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(UserPreferences.this, "ERROR", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<DiscountPreferencesPostResponse> call, Throwable t) {
                    call.cancel();
                    Toast.makeText(UserPreferences.this,"Fail to connect with server",Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void fetchCategories() {
        Call<List<Category>> call = iuserService.fetchCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                UserPreferences.this.categories = response.body();
                List<String> temp = new ArrayList<>();
                for(int i=0;i<categories.size();i++){
                    temp.add(categories.get(i).getTitle());
                }
                UserPreferences.this.catTemp.addAll(temp);
                spinContentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(UserPreferences.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserPreferences.this, UserPreferenceList.class);
        startActivity(intent);
    }
}