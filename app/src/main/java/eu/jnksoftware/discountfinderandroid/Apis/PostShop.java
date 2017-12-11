package eu.jnksoftware.discountfinderandroid.Apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostShop {

    @SerializedName("brandName")
    @Expose
    private String brandName;

    @SerializedName("logPos")
    @Expose
    private double logPos;

    @SerializedName("latPos")
    @Expose
    private double latPost;

    public PostShop(String brandName, double logPos, double latPost) {
        this.brandName = brandName;
        this.logPos = logPos;
        this.latPost = latPost;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getLogPos() {
        return logPos;
    }

    public void setLogPos(double logPos) {
        this.logPos = logPos;
    }

    public double getLatPost() {
        return latPost;
    }

    public void setLatPost(double latPost) {
        this.latPost = latPost;
    }
}
