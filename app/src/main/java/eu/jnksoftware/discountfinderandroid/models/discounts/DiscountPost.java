package eu.jnksoftware.discountfinderandroid.models.discounts;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountPost {
    @SerializedName("shopId")
    @Expose
    private int shopId;

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

    @SerializedName("imageBase")
    @Expose
    private String imageBase;

    @SerializedName("imageTitle")
    @Expose
    private String imageTitle;

    public int getShopId() {

        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageBase() {
        return imageBase;
    }

    public void setImageBase(String imageBase) {
        this.imageBase = imageBase;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
