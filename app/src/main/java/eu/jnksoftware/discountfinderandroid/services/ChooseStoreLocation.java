package eu.jnksoftware.discountfinderandroid.services;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;

public class ChooseStoreLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng currentLatLng;
    Location userLocation = new Location();
    boolean hasMarker = false;
    String auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_store_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Button searchButton = findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(searchClick);
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        initializeMapTypes();
        userLocation.setLatitude(getIntent().getDoubleExtra("lat", 100));
        userLocation.setLongitude(getIntent().getDoubleExtra("lon", 100));
        auth = getIntent().getStringExtra("auth");
        currentLatLng = new LatLng(userLocation.getLatitude(),userLocation.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                checkMarkers(latLng);
                Intent intent = getIntent();
                intent.putExtra("streetName",getStreetName(getBaseContext(),latLng));
                intent.putExtra("storeLat",latLng.latitude);
                intent.putExtra("storeLon",latLng.longitude);
                intent.putExtra("lat",userLocation.getLatitude());
                intent.putExtra("lon",userLocation.getLongitude());
                intent.putExtra("auth",auth);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private final View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText searchEditText = findViewById(R.id.searchEditText);
            String searchLocation = searchEditText.getText().toString();
            List<Address> addresses = null;
            if(searchLocation!=null || searchLocation.equals("")){
                Geocoder geocoder = new Geocoder(getBaseContext());
                try{
                    addresses = geocoder.getFromLocationName(searchLocation,1);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                Address address = addresses.get(0);
                LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
               // mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                checkMarkers(latLng);
                addresses.remove(0);
            }
        }
    };

    private void initializeMapTypes(){
        Button sateliteButton = findViewById(R.id.sateliteBtn);
        sateliteButton.setOnClickListener(sateliteClickListener);
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE){
            sateliteButton.setText("Terrain");
        }
        else{
            sateliteButton.setText("Satelite");
        }

    }

    private final View.OnClickListener sateliteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            } else {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
            initializeMapTypes();
        }
    };

    public void checkMarkers(LatLng latLng){
        if (!hasMarker) {
            mMap.addMarker(createMarker(latLng));
            hasMarker = true;
        } else {
            mMap.clear();
            mMap.addMarker(createMarker(latLng));
        }
    }

    public String getStreetName(Context context, LatLng latLng){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        double lat = latLng.latitude;
        double lon = latLng.longitude;
        try {
            List<Address> addresses = geocoder.getFromLocation(lat,lon,1);
            Address address = addresses.get(0);
            String name =  address.getFeatureName();
            return name;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        public MarkerOptions createMarker(LatLng latLng){
            MarkerOptions markerOptions = new MarkerOptions();
            String formattedLatLng = latLng.toString().substring(0, 4);
            markerOptions.position(latLng).title(formattedLatLng);
            return markerOptions;
        }

}
