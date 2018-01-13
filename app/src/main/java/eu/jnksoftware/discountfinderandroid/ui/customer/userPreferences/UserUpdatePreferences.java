package eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUpdatePreferences extends AppCompatActivity {

    private User user;
    private int seekBarProgress = 0;
    private TextView showSeekProgress;
    private List<Category> categories = new ArrayList<>();
    private List<String> catTemp = new ArrayList<>();
    private String category;
    private String price;
    private String tags;
    IuserService iuserService;
    EditText tagUpdatePref;
    EditText idUpdatePref;
    String accessToken;
    private ArrayAdapter<String> spinContentAdapter;
    private Spinner spinnerCat;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_preferences);
        String accessToken;
        iuserService= ApiUtils.getUserService();
        idUpdatePref =findViewById(R.id.idPrefText);
        tagUpdatePref=findViewById(R.id.tagPrefText);

        user= ManageSharePrefs.readUser(null);
        
        spinnerCat = findViewById(R.id.spinnerCategory);
        spinContentAdapter = new ArrayAdapter<>(UserUpdatePreferences.this,android.R.layout.simple_list_item_1, catTemp);
        spinnerCat.getBackground().setAlpha(130);
        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);

        fetchCategories();



        SeekBar seekBarPrice = findViewById(R.id.seekBarPrice);
        showSeekProgress = findViewById(R.id.tvSeekBarValue);
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

            category=String.valueOf(categories.get((int) spinnerCat.getSelectedItemId()).getId());
            price=String.valueOf(seekBarProgress);
            tags=tagUpdatePref.getText().toString().trim();
            int idpref;
            String s1;
            s1=idUpdatePref.getText().toString();
            idpref=Integer.parseInt(s1);

            doUpdateUserPreference(idpref,category,price,tags);

            //Toast.makeText(UserUpdatePreferences.this, discountPreferencesRequest.getCategory(), Toast.LENGTH_SHORT).show();
        }
    };

    public void doUpdateUserPreference(final int id, String cat,String price,String tags) {

        Call<DiscountPreferencesResponse> call =iuserService.putDiscountPreferences(id,cat,price,tags,"Bearer "+user.getAccessToken());
        call.enqueue(new Callback<DiscountPreferencesResponse>() {
            @Override
            public void onResponse(Call<DiscountPreferencesResponse> call, Response<DiscountPreferencesResponse> response) {
                Toast.makeText(UserUpdatePreferences.this,"Η προτίμηση με id"+id+"Αλλαξε επιτυχως!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DiscountPreferencesResponse> call, Throwable t) {

            }
        });

    }

    private void fetchCategories() {
        Call<List<Category>> call = iuserService.fetchCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                UserUpdatePreferences.this.categories = response.body();
                List<String> temp = new ArrayList<>();
                for(int i=0;i<categories.size();i++){
                    temp.add(categories.get(i).getTitle());
                }
                UserUpdatePreferences.this.catTemp.addAll(temp);
                spinContentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(UserUpdatePreferences.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
            }

        });
    }


}