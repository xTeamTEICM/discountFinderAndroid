package eu.jnksoftware.discountfinderandroid.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import eu.jnksoftware.discountfinderandroid.services.GeoLocationService;

public class Main extends AppCompatActivity {

    private GeoLocationService geoLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geoLocationService = new GeoLocationService(this);
        geoLocationService.requestPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                geoLocationService.stopUsingGPS();
                startActivity(new Intent(Main.this, Login.class));
                finish();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(permissions[0]) || shouldShowRequestPermissionRationale(permissions[1])) {
                        geoLocationService.requestPermissions();
                    } else {
                        new AlertDialog.Builder(this)
                                .setCancelable(false)
                                .setTitle("No GPS Permissions")
                                .setMessage("We can't use the GPS, because you have perma denied the GPS permission. The app will close !")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                }
            }
        }
    }
}