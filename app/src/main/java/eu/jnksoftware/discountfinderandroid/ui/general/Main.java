package eu.jnksoftware.discountfinderandroid.ui.general;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;


public class Main extends AppCompatActivity {

    private GeoLocation geoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        geoLocation = new GeoLocation(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            geoLocation.requestPermissions(this);
        } else {
            openLogin();
        }
    }


        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openLogin();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(permissions[0]) || shouldShowRequestPermissionRationale(permissions[1])) {
                        geoLocation.requestPermissions(Main.this);
                    } else {
                        new AlertDialog.Builder(this)
                                .setCancelable(false)
                                .setTitle("Αδειες GPS")
                                .setMessage("Δεν εχουμε προσβαση στο GPS σας. Η εφαρμογη θα τερματιστει !")
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

    private void openLogin() {
        Intent intent = new Intent(Main.this,Login.class);
        startActivity(intent);
        finish();
    }
}