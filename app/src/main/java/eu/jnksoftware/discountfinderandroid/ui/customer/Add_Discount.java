package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;

public class Add_Discount extends AppCompatActivity {

    Spinner spinnerCat;
    ArrayAdapter<String> spinContentAdapter;
    SeekBar seekbarPrice;
    int seekBarProgress=0;
    TextView showSeekProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__discount);

        spinnerCat = (Spinner) findViewById(R.id.spinnerCategory);
        spinContentAdapter = new ArrayAdapter<String>(Add_Discount.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.categories));
        seekbarPrice = (SeekBar) findViewById(R.id.seekBarPrice);
        showSeekProgress = (TextView) findViewById(R.id.tvSeekBarValue);

        showSeekProgress.setText("Μέχρι "+seekBarProgress + " ευρώ");
        seekbarPrice.setMax(150);
        seekbarPrice.setProgress(seekBarProgress);

        seekbarPrice.getBackground().setAlpha(200);
        spinnerCat.getBackground().setAlpha(130);


        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);

        seekbarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
}
