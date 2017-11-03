package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import eu.jnksoftware.discountfinderandroid.R;

public class Add_Discount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__discount);

        Spinner spinnerCat = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> spinContentAdapter = new ArrayAdapter<String>(Add_Discount.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.categories));
        SeekBar seekbarPrice = (SeekBar) findViewById(R.id.seekBarPrice);

        seekbarPrice.getBackground().setAlpha(255);
        spinnerCat.getBackground().setAlpha(130);


        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);
    }
}
