package eu.jnksoftware.discountfinderandroid.models;

import android.net.Uri;

import java.io.Serializable;


public class Shop implements Serializable {

    private final int id;
    private final String ownerId;
    private final Location location;


    public Shop(int id, String ownerId, Location location) {
        this.id = id;
        this.ownerId = ownerId;
        this.location = location;
    }

    private double calculateDistance(Location position) {
        double distance;
        double subtractionX = Math.abs(position.getLatitude() - location.getLatitude());
        double subtractionY = Math.abs(position.getLongitude() - location.getLongitude());
        distance = Math.sqrt((Math.pow(subtractionX, 2)) + (Math.pow(subtractionY, 2)));
        return distance;
    }

    public Uri getMapsUri() {

        Double Latitude = location.getLatitude();
        Double Longitude = location.getLongitude();
        String labelLocation = "x-Team Sample : " + ownerId;

        return Uri.parse(
                "geo:" +
                        "<" + Latitude + ">,<" + Longitude + ">" +
                        "?q=<" + Latitude + ">,<" + Longitude + ">(" + labelLocation + ")"
        );
    }

    // this toString prints on ListView
    public String toString(){

        return this.ownerId;
    }

    public int getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Location getLocation() {
        return location;
    }
}
