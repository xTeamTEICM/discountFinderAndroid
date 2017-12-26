package eu.jnksoftware.discountfinderandroid.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("logPos")
    @Expose
    private double logPos;
    @SerializedName("latPos")
    @Expose
    private double latPos;

    public Location() {
    }

    public Location(double logPos, double latPos) {
        this.logPos = logPos;
        this.latPos = latPos;
    }

    public double getLogPos() {
        return logPos;
    }

    public void setLogPos(double logPos) {
        this.logPos = logPos;
    }

    public double getLatPos() {
        return latPos;
    }

    public void setLatPos(double latPos) {
        this.latPos = latPos;
    }

}
