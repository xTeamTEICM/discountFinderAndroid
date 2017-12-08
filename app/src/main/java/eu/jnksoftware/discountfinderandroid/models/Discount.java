package eu.jnksoftware.discountfinderandroid.models;

public class Discount {

    private  int id;
    private Category category;
    private String shortDescription;
    private String shopName;
    private float finalPrice;
    private String productImageUrl;
    private double distance;

    public Discount(int id , Category category, String shortDescription, String shopName, float finalPrice, String productImageUrl,double distance) {
        this.id = id;
        this.category = category;
        this.shortDescription = shortDescription;
        this.shopName = shopName;
        this.finalPrice = finalPrice;
        this.productImageUrl = productImageUrl;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImage) {
        this.productImageUrl = productImageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
