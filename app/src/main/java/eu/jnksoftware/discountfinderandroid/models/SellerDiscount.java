package eu.jnksoftware.discountfinderandroid.models;


public class SellerDiscount {
    private int id;
    private int shopId;
    private int category;
    private double originalPrice;
    private double currentPrice;
    private String description;
    private String image;

    public SellerDiscount(int shopId, int category, double originalPrice, double currentPrice, String description, String image) {
        this.shopId = shopId;
        this.category = category;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.image = image;
    }

    public int getShopId() {
        return shopId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getId(){ return id; }

    public int getCategory() {
        return category;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public double getDiscountPercent(){
        return ((this.originalPrice - this.currentPrice)/this.originalPrice)*100;
    }
}
