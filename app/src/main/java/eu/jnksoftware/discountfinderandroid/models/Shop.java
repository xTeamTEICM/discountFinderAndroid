package eu.jnksoftware.discountfinderandroid.models;

import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;

public class Shop implements Serializable {

    private final String shopName;
    private final Position location;
    private final double distanceFromUser;

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
        return Intent.createChooser(intent, "Launch Maps");


    }
    // this toString prints on ListView
    public String toString(){

        return this.shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public Position getLocation() {
        return location;
    }

    public double getDistanceFromUser() {
        return distanceFromUser;
    }
}
