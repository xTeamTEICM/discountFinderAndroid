package eu.jnksoftware.discountfinderandroid.models.discounts;

public class Discount {

    private  int discountId;
    private String category;
    private String shortDescription;
    private String shopName;
    private float finalPrice;
    private String productImageURL;
    private double distance;
    private double shopLatPos;
    private double shopLogPos;

    public Discount(int discountId , String category, String shortDescription, String shopName, float finalPrice, String productImageURL,double distance, double shopLogPos, double shopLatPos) {
        this.discountId = discountId;
        this.category = category;
        this.shortDescription = shortDescription;
        this.shopName = shopName;
        this.finalPrice = finalPrice;
        this.productImageURL = productImageURL;
        this.distance = distance;
        this.shopLatPos = shopLatPos;
        this.shopLogPos = shopLogPos;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setid(int id) {
        this.discountId = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImage) {
        this.productImageURL = productImageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getShopLatPos() {
        return shopLatPos;
    }

    public double getShopLogPos() {
        return shopLogPos;
    }
}
