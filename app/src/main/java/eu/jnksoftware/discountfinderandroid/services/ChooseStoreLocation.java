package eu.jnksoftware.discountfinderandroid.services;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;

public class ChooseStoreLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng currentLatLng;
    boolean hasMarker = false;

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        initializeMapTypes();
        currentLatLng = new LatLng(getIntent().getDoubleExtra("lat",100)
                ,getIntent().getDoubleExtra("lon",100));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                checkMarkers(latLng);
                Intent intent = getIntent();
                String cityName = findCity(latLng);
                intent.putExtra("streetName",getStreetName(getBaseContext(),latLng));
                intent.putExtra("storeLat",latLng.latitude);
                intent.putExtra("storeLon",latLng.longitude);
                intent.putExtra("cityName",cityName);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private String findCity(LatLng latLng){
        Address address;
        String cityName = "Unknown city";
        double lat = latLng.latitude;
        double lon = latLng.longitude;
        Geocoder geocoder = new Geocoder(getBaseContext());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat,lon,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        address = addresses.get(0);
        if (address!=null && address.getLocality()!= null) {
            cityName = address.getLocality();
        }
        return cityName;
    }

    private final View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText searchEditText = findViewById(R.id.searchEditText);
            String searchLocation = searchEditText.getText().toString();
            List<Address> addresses = null;
            if(searchLocation!=null && !searchLocation.equals("")) {
                Geocoder geocoder = new Geocoder(getBaseContext());
                try {
                    addresses = geocoder.getFromLocationName(searchLocation, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses.isEmpty()) {
                    Toast.makeText(ChooseStoreLocation.this,"There is no place with this name",Toast.LENGTH_SHORT).show();
                } else {
                    Address address = addresses.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    checkMarkers(latLng);
                    addresses.remove(0);
                }
            }
            else Toast.makeText(ChooseStoreLocation.this,"Insert a valid place",Toast.LENGTH_SHORT).show();
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
