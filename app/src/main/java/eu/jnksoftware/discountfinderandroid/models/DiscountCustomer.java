package eu.jnksoftware.discountfinderandroid.models;

import android.graphics.Bitmap;
import android.widget.Toast;

/**
 *
 * Created by Sotiris on 26/10/2017.
 *
 */

public class DiscountCustomer {

    private  int id;
    private String shortDescription;
    private String shopName;
    private float finalPrice;
    private Bitmap productImage;

    public DiscountCustomer(int id, String shortDescription, String shopName, float finalPrice, Bitmap productImage) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.shopName = shopName;
        this.finalPrice = finalPrice;
        this.productImage = productImage;
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

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }
}
