package eu.jnksoftware.discountfinderandroid.models;

import java.io.Serializable;

/**
 * Created by iordkost on 25/10/2017.
 */

public class Location {
    private double latitude;
    private double longitude;

    public Location() {
        latitude = 0;
        longitude = 0;
    }

    public Location(android.location.Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
