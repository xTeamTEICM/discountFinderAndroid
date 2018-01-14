package eu.jnksoftware.discountfinderandroid.ui.customer.discount;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import eu.jnksoftware.discountfinderandroid.R;


public class DiscountLocation extends Fragment implements OnMapReadyCallback {
    GoogleMap myGoogleMap;
    MapView myMapView;
    View myView;


    public DiscountLocation(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        myView = inflater.inflate(R.layout.activity_discount_location,container,false);
        return myView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        myMapView = myView.findViewById(R.id.discount_maps);

        if(myMapView != null){
            myMapView.onCreate(null);
            myMapView.onResume();
            myMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        myGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(40,23)).title("Discount"));
        CameraPosition discount = CameraPosition.builder().target(new LatLng(40,23)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(discount));
    }
}