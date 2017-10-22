package eu.jnksoftware.discountfinderandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Shop extends AppCompatActivity {

    String shopName;
    Position location;
    double distanceFromUser;

    public Shop(String shopName, Position location,double distanceFromUser) {
        this.shopName = shopName;
        this.location = location;
        this.distanceFromUser = distanceFromUser;
    }

    public Intent openOnMaps(){

        Double Latitude = location.getX();
        Double Longitude = location.getY();
        String labelLocation = "x-Team Sample : " + shopName;
        Intent intent=new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("geo:<" + Latitude  + ">,<" + Longitude + ">?q=<" + Latitude  + ">,<" + Longitude + ">(" + labelLocation+ ")"));

        //if user has not the application googleMaps it will show him a dialog with Launch Maps
        Intent chooser=Intent.createChooser(intent,"Launch Maps");
        return chooser;


    }
    // this toString prints on ListView
    public String toString(){

        return this.shopName;
    }

}
