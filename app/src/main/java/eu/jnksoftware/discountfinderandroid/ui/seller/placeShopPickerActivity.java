package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import eu.jnksoftware.discountfinderandroid.R;

public class placeShopPickerActivity extends AppCompatActivity {

    int placePickerRequest=1;
    TextView tvPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_shop_picker);
        tvPlace=(TextView)findViewById(R.id.textPlace);

    }


    public void goPlacePicker(View view){

        PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();

        try{
            startActivityForResult(builder.build(placeShopPickerActivity.this),placePickerRequest);
        }catch(GooglePlayServicesRepairableException e)
        {
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode==placePickerRequest){
            if(resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(placeShopPickerActivity.this,data);
                tvPlace.setText(String.valueOf(place.getLatLng()));
            }

        }

    }
}
