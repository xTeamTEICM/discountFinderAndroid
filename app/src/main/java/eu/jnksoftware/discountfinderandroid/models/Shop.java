package eu.jnksoftware.discountfinderandroid.models;

import android.net.Uri;

public class Shop {

    private final int id;
    private String name;
    private final int ownerId;
    private final Location location;


    public Shop(int id, String name,int ownerId, Location location) {
        this.id = id;
        this.name = name;
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

        return this.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public Location getLocation() {
        return location;
    }
}
