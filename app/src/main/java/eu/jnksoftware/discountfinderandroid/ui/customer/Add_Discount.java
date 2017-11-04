package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import eu.jnksoftware.discountfinderandroid.R;

public class Add_Discount extends AppCompatActivity {

    private int seekBarProgress=0;
    private TextView showSeekProgress;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__discount);

        Spinner spinnerCat = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> spinContentAdapter = new ArrayAdapter<>(Add_Discount.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        SeekBar seekBarPrice = (SeekBar) findViewById(R.id.seekBarPrice);
        showSeekProgress = (TextView) findViewById(R.id.tvSeekBarValue);
        Button addButton = (Button) findViewById(R.id.btAddDiscount);

        addButton.setOnClickListener(btAddDiscountClick);

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

    private final View.OnClickListener btAddDiscountClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO:
        }
    };
}
