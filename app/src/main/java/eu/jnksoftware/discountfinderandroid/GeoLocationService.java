package eu.jnksoftware.discountfinderandroid;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by makis on 21/10/2017.
 */
public class GeoLocationService {
    Activity origin;
    private Location location;
    LocationManager locationManager;

    public GeoLocationService(Activity origin) {
        this.origin = origin;
        locationManager = (LocationManager) origin.getSystemService(LOCATION_SERVICE);
    }

    public boolean hasNotPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (origin.checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (origin.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            origin.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission
                    .ACCESS_COARSE_LOCATION}, 100);
        }
    }

    public void update() throws SecurityException {
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public Location getLocation() {
        return location;
    }

    public double getLat() {
        return location.getLatitude();
    }

    public double getLon() {
        return location.getLongitude();
    }

    public String getLocationAsString() {
        return "Location: Lat = " + getLat() + " Lon = " + getLon();
    }

}
