package eu.jnksoftware.discountfinderandroid.models;

/**
 *
 * Created by poz on 27/10/2017.
 *
 */

public class DiscountRequest {
    private int id;
    private String category;
    private double maxPrice;
    private String tags;

    public DiscountRequest(int id, String category, double maxPrice,String tags) {
        this.id = id;
        this.category = category;
        this.maxPrice = maxPrice;
        this.tags = tags;
    }


    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
