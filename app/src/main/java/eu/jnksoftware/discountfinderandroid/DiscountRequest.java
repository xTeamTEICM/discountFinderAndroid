package eu.jnksoftware.discountfinderandroid;

import java.util.List;

/**
 *
 * Created by poz on 27/10/2017.
 *
 */

public class DiscountRequest {
    private int id;
    private List<String> category;
    private double maxPrice;

    public DiscountRequest(int id, List<String> category, double maxPrice) {
        this.id = id;
        this.category = category;
        this.maxPrice = maxPrice;
    }

    public int getId() {
        return id;
    }


    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}