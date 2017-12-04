package eu.jnksoftware.discountfinderandroid.models;

import android.net.Uri;

public class Shop {

    private final int id;
    private final int ownerId;
    private String brandName;
    private final Location location;


    public Shop(int id,int ownerId,String brandName, Location location) {
        this.id = id;
        this.ownerId = ownerId;
        this.brandName = brandName;
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

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setName(String brandName) {
        this.brandName = brandName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public Location getLocation() {
        return location;
    }
}
