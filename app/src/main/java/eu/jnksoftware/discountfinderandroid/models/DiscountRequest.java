package eu.jnksoftware.discountfinderandroid.models;


import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.api.APIConfig;
import eu.jnksoftware.discountfinderandroid.api.CategoryAPI;
import eu.jnksoftware.discountfinderandroid.services.POSTNetwork;

/**
 *
 * Created by poz on 27/10/2017.
 *
 */

public class DiscountRequest {
    private int id;
    private Category category;
    private double price;
    private String tags;

    public DiscountRequest(int id, Category category, double price,String tags) throws Exception {

            this.id = id;
            this.category = category;
            this.price = price;
            this.tags = tags;

    }



    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}
