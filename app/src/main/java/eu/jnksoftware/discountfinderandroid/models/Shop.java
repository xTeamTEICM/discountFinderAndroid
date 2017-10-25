package eu.jnksoftware.discountfinderandroid.models;

import android.net.Uri;

import java.io.Serializable;

public class Shop implements Serializable {

    private final String name;
    private final Location location;
    private double distanceFromUser;

    public Shop(String name, Location location, Location userLocation) {
        this.name = name;
        this.location = location;
        this.distanceFromUser = calculateDistance(userLocation);
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
        String labelLocation = "x-Team Sample : " + name;

        return Uri.parse(
                "geo:" +
                        "<" + Latitude + ">,<" + Longitude + ">" +
                        "?q=<" + Latitude + ">,<" + Longitude + ">(" + labelLocation + ")"
        );
    }

    // this toString prints on ListView
    public String toString(){

        return this.name;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public double getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(double distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }
}
