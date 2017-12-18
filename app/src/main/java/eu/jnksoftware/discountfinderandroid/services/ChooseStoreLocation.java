package eu.jnksoftware.discountfinderandroid.services;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.ui.customer.SellerAddShop;

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
        mMap = googleMap;
        userLocation.setLatitude(getIntent().getDoubleExtra("lat", 100));
        userLocation.setLongitude(getIntent().getDoubleExtra("lon", 100));
        auth = getIntent().getStringExtra("auth");
        currentLatLng = new LatLng(userLocation.getLatitude(),userLocation.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13));
        mMap.addCircle(customizeCircle(1000,currentLatLng));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                checkMarkers(latLng);
                Intent intent = getIntent();
                intent.putExtra("storeLat",latLng.latitude);
                intent.putExtra("storeLon",latLng.longitude);
                intent.putExtra("lat",userLocation.getLatitude());
                intent.putExtra("lon",userLocation.getLongitude());
                intent.putExtra("auth",auth);
                setResult(RESULT_OK,intent);
                finish();
                setResult(RESULT_CANCELED);
            }
        });
    }

    public void checkMarkers(LatLng latLng){
        if (hasMarker == false) {
            mMap.addMarker(createMarker(latLng));
            hasMarker = true;
        } else {
            mMap.clear();
            mMap.addCircle(customizeCircle(1000,currentLatLng));
            mMap.addMarker(createMarker(latLng));
        }
    }

        public MarkerOptions createMarker(LatLng latLng){
            MarkerOptions markerOptions = new MarkerOptions();
            String formattedLatLng = latLng.toString().substring(0, 4);
            markerOptions.position(latLng).title(formattedLatLng);
            return markerOptions;
        }

        public CircleOptions customizeCircle(double radius,LatLng latLng){
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.fillColor(R.color.cast_intro_overlay_button_background_color);
        circleOptions.visible(true);
        circleOptions.radius(radius);
        return circleOptions;
    }
}
