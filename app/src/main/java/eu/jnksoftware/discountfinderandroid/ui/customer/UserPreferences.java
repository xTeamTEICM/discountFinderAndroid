package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.Apis.CategoriesAPI;
import eu.jnksoftware.discountfinderandroid.R;

public class UserPreferences extends AppCompatActivity {

    private int seekBarProgress=0;
    private TextView showSeekProgress;
     public ArrayList<String> categories = new ArrayList<>();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        Spinner spinnerCat = findViewById(R.id.spinnerCategory);
        CategoriesAPI categoriesAPI = new CategoriesAPI();
        categories = categoriesAPI.getCategories(UserPreferences.this);
        ArrayAdapter<String> spinContentAdapter = new ArrayAdapter<>(UserPreferences.this, android.R.layout.simple_list_item_1,categories);
        SeekBar seekBarPrice = findViewById(R.id.seekBarPrice);
        showSeekProgress = findViewById(R.id.tvSeekBarValue);
        Button savePrefButton = findViewById(R.id.btSavePreferences);

        savePrefButton.setOnClickListener(savePrefClick);

        showSeekProgress.setText("Μέχρι "+seekBarProgress + " ευρώ");
        seekBarPrice.setMax(150);
        seekBarPrice.setProgress(seekBarProgress);

        seekBarPrice.getBackground().setAlpha(200);
        spinnerCat.getBackground().setAlpha(130);


        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);

        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress=i;
                showSeekProgress.setText("Μέχρι "+seekBarProgress + " ευρώ");
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
            //TODO:
        }
    };
}
