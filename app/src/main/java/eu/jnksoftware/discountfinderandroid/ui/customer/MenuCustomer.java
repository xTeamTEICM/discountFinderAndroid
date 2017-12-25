package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.HttpCall;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.recyclers.DiscountCustomerRecyclerList;
import eu.jnksoftware.discountfinderandroid.ui.customer.shops.SellerShops;
import eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences.UserPreferenceList;
import eu.jnksoftware.discountfinderandroid.ui.general.AboutUs;
import eu.jnksoftware.discountfinderandroid.ui.general.Settings;
public class MenuCustomer extends AppCompatActivity {

    private GeoLocation geoLocation;
    private User tempuser;
    private IuserService iuserService;
    private String auth;
    private HttpCall httpCall;
    private Location myLocation;
    String locationResponse;
    String rawData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_customer);
        iuserService= ApiUtils.getUserService();


       tempuser= ManageSharePrefs.readUser( null);
        if (tempuser!=null){
            Toast.makeText(getApplicationContext(), "Token :"+ tempuser.getTokenType(), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MenuCustomer.this, "nothing", Toast.LENGTH_SHORT).show();
        }

        auth = "Bearer " + tempuser.getAccessToken();

        geoLocation = new GeoLocation(this);

        if (geoLocation.canGetLocation()) {

            myLocation=new Location(geoLocation.getLongitude(),geoLocation.getLatitude());
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + myLocation.getLatPos() + "\nLong: " + myLocation.getLogPos(), Toast.LENGTH_LONG).show();
        }
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("logPos", myLocation.getLogPos());
            paramObject.put("latPos", myLocation.getLatPos());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        httpCall=new HttpCall();
        locationResponse=httpCall.setUserLocation(paramObject.toString(),auth);
        Toast.makeText(getApplicationContext(), "location response: "+locationResponse, Toast.LENGTH_LONG).show();

        Button about = findViewById(R.id.aboutBtn);
        about.setOnClickListener(aboutClick);
        Button settings = findViewById(R.id.settingsBtn);
        settings.setOnClickListener(settingsClick);

        Button myShops =  findViewById(R.id.showShopsButton);
        myShops.setOnClickListener(showShopsButtonClick);
        Button filtersBtn =  findViewById(R.id.filtersBtn);
        filtersBtn.setOnClickListener(filtersButtonClick);
        Button myDiscount = findViewById(R.id.showDiscountsBtn);
        myDiscount.setOnClickListener(discountClick);


    }

    private final View.OnClickListener showShopsButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuCustomer.this, SellerShops.class);
            Gson user = new Gson();
            intent.putExtra("auth", auth);
            intent.putExtra("lat",geoLocation.getLatitude());
            intent.putExtra("lon",geoLocation.getLongitude());
            intent.putExtra("User", user.toJson(tempuser));

            startActivity(intent);
        }
    };

    private final View.OnClickListener discountClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (geoLocation.getLocation() != null) {
                try {
                    Intent intent=new Intent(MenuCustomer.this,DiscountCustomerRecyclerList.class);
                    auth = tempuser.getAccessToken();
                    intent.putExtra("auth", auth);
                    intent.putExtra("latitude", geoLocation.getLatitude());
                    intent.putExtra("longitude", geoLocation.getLongitude());
                    startActivity(intent);

                } catch (Exception ex) {
                    Toast.makeText(MenuCustomer.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(MenuCustomer.this, "We don't have your location yet !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener aboutClick = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            startActivity(new Intent(MenuCustomer.this, AboutUs.class));
        }
    };

    private final View.OnClickListener settingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = findViewById(R.id.showShopsButton);
            Intent intent = new Intent(MenuCustomer.this, Settings.class);
            intent.putExtra("isSellerEnabled", button.isShown());
            startActivity(intent);
        }
    };

    private final View.OnClickListener filtersButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Gson user=new Gson();
            Intent userPreferences=new Intent(MenuCustomer.this,UserPreferenceList.class);
            userPreferences.putExtra("User", user.toJson(tempuser));
            startActivity(userPreferences);
        }
    };

    private final View.OnClickListener shopClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            startActivity(new Intent(MenuCustomer.this, AboutUs.class));
        }
    };

    boolean doubleBackPressed = false;
    @Override
    public void onBackPressed() {

        if (doubleBackPressed) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            System.exit(0);
        }doubleBackPressed = true;

        Toast.makeText(MenuCustomer.this,"Please press BACK again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                doubleBackPressed = false;

            }
        }, 3000);
        doubleBackPressed = true;
    }

}