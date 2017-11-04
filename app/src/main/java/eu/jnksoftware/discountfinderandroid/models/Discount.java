package eu.jnksoftware.discountfinderandroid.models;

import android.media.Image;
import android.widget.Toast;

/**
 *
 * Created by Sotiris on 26/10/2017.
 *
 */

public class Discount {

    private  int id;
    private  int shopId;
    private Category category;
    private String desc;
    private String photoUrl;
    private double originalPrice;
    private double limitedPrice;
    private Image image;


    public Discount(int id, int shopId, Category category, String photoUrl, String desc, double originalPrice, double limitedPrice, Image image) throws Exception {
        try {
            this.id = id;
            this.shopId = shopId;
            this.category = category;
            this.desc = desc;
            this.originalPrice = originalPrice;
            this.limitedPrice = limitedPrice;
            this.image = image;
            this.photoUrl = photoUrl;
        }catch (Exception ignored){

        }
        if (!isValidPrices(originalPrice,limitedPrice))throw new Exception("only positive prices accepted");
        if(!isDiscountPriceLower())throw new Exception("Your discount price is bigger than original");


    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getLimitedPrice() {
        return limitedPrice;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setLimitedPrice(double limitedPrice) {
        this.limitedPrice = limitedPrice;
    }

    public boolean isValidPrices(double originalPrice,double limitedPrice){
        return originalPrice >= 0 && limitedPrice >= 0;
    }

    public boolean isDiscountPriceLower(){
        return limitedPrice < originalPrice;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}