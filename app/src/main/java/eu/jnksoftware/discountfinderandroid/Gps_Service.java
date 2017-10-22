package eu.jnksoftware.discountfinderandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by makis on 21/10/2017.
 */

public class Gps_Service extends Service {

    private Location mylocation;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        locationListener =new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mylocation = location;
                //locationManager.removeUpdates(locationListener);

                Intent intent=new Intent("location_update");
                // intent.putExtra("latitude",location.getLatitude());
                // intent.putExtra("longitude",location.getLongitude());

                Bundle params = new Bundle();
                params.putDouble("latitude",location.getLatitude());
                params.putDouble("longitude",location.getLongitude());
                intent.putExtras(params);
                sendBroadcast(intent);
                locationManager.removeUpdates(locationListener);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        };

        locationManager= (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,locationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager !=null){
            locationManager.removeUpdates(locationListener);
        }
    }
}

