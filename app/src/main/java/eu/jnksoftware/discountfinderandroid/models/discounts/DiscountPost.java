package eu.jnksoftware.discountfinderandroid.models.discounts;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountPost {
    @SerializedName("category")
    @Expose
    private int category;

    @SerializedName("originalPrice")
    @Expose
    private double originalPrice;

    @SerializedName("currentPrice")
    @Expose
    private double currentPrice;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String image;

    public DiscountPost(int category, double originalPrice, double currentPrice, String description, String image) {
        this.category = category;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.image = image;
    }
}
