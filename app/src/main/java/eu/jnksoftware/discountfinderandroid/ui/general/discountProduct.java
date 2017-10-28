package eu.jnksoftware.discountfinderandroid.ui.general;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by nikos on 28/10/2017.
 */

public class discountProduct implements Serializable{

    private int discountImage;
    private String discountName;
    private BigDecimal discountPrice;

    public int getDiscountImage() {
        return discountImage;
    }

    public void setDiscountImage(int discountImage) {
        this.discountImage = discountImage;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public discountProduct(int discountImage, String discountName, BigDecimal discountPrice) {
        this.discountImage = discountImage;
        this.discountName = discountName;
        this.discountPrice = discountPrice;
    }

    public discountProduct() {

    }



}
