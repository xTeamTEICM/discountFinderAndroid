package eu.jnksoftware.discountfinderandroid.services;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import eu.jnksoftware.discountfinderandroid.R;


public class ChooseStoreLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng currentLatLng;
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
        mMap = googleMap;
        double lat = getIntent().getDoubleExtra("lat", 100);
        double lon = getIntent().getDoubleExtra("lon", 100);
        currentLatLng = new LatLng(lat, lon);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13));
        mMap.addCircle(customizeCircle(1000,currentLatLng));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                checkMarkers(latLng);
            }
        });
    }

    public void checkMarkers(LatLng latLng){
        if (!hasMarker) {
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
