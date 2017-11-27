package eu.jnksoftware.discountfinderandroid.ui.general;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;

public class Main extends AppCompatActivity {

    private GeoLocation geoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        geoLocation = new GeoLocation(this);
        try {
            writeSaveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            geoLocation.requestPermissions(this);
        } else {
            openLogin();
        }
    }

    public void writeSaveFile() throws IOException {
        String filename = "saveFile";
        String seller = "no";
        FileOutputStream outputStream;
        if(saveFileExists()==false){
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(seller.getBytes());
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        private boolean saveFileExists(){
            FileInputStream inputStream = null;
            try {
                inputStream = openFileInput("saveFile");
                if (inputStream != null) {
                    return true;
                } else {
                    return false;
                }
            }catch (FileNotFoundException e){
                return false;
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