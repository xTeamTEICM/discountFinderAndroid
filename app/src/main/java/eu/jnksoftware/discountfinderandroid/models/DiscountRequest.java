package eu.jnksoftware.discountfinderandroid.models;


import android.media.Image;

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
    private  String ownerId;
    private Category category;
    private double price;
    private String tags;
    private Image image;

    public DiscountRequest(int id, String ownerId, Category category, double price,String tags, Image image) throws Exception {

            this.id = id;
            this.ownerId = ownerId;
            this.category = category;
            this.price = price;
            this.tags = tags;
            this.image = image;

    }


    public int getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
