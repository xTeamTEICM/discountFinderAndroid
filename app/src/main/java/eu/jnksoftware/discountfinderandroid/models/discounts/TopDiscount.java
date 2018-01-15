package eu.jnksoftware.discountfinderandroid.models.discounts;


public class TopDiscount {
    private String shopName;
    private String category;
    private String shortDescription;
    private double finalPrice;
    private String productImageURL;
    private int discountId;
    private int distance;
    private double shopLatPos;
    private double shopLogPos;

    public TopDiscount(String shortDescription, String productImage) {
        this.shortDescription = shortDescription;
        this.productImageURL = productImage;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getProductImage() {
        return productImageURL;
    }

    public int getDiscountId() {
        return discountId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getCategory() {
        return category;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getProductImageURL() {
        return productImageURL;
    }
}
