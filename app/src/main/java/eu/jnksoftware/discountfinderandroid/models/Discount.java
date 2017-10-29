package eu.jnksoftware.discountfinderandroid.models;

import android.widget.Toast;

/**
 * Created by Sotiris on 26/10/2017.
 */

public class Discount {

    private  int id;
    private Category category;
    private String photoUrl;
    private double originalPrice;
    private double limitedPrice;


    public Discount(Category category, double originalPrice, double limitedPrice,int id,String photoUrl) throws Exception {
         try {
             this.category = category;
             this.originalPrice = originalPrice;
             this.limitedPrice = limitedPrice;
             this.id = id;
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
}
