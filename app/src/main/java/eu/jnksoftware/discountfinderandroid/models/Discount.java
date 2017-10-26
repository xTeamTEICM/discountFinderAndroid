package eu.jnksoftware.discountfinderandroid.models;

import android.widget.Toast;

/**
 * Created by xNeo on 26/10/2017.
 */
//TODO testing
public class Discount {

    private  int id;
    private String category;
    private String photoUrl;
    private float originalPrice;
    private float limitedPrice;


    //TODO message to user if  isValidPrice =false
    public Discount(String category, float originalPrice, float limitedPrice,int id,String photoUrl) {
        if (isValidPrice(originalPrice,limitedPrice)) {
            this.category = category;
            this.originalPrice = originalPrice;
            this.limitedPrice = limitedPrice;
            this.id = id;
            this.photoUrl = photoUrl;
        }

    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public float getLimitedPrice() {
        return limitedPrice;
    }



    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setLimitedPrice(float limitedPrice) {
        this.limitedPrice = limitedPrice;
    }



    private boolean isValidPrice(float originalPrice,float limitedPrice){
        if(originalPrice>=0&&limitedPrice>=0)
            return true;
        else
            return false;
    }
}
