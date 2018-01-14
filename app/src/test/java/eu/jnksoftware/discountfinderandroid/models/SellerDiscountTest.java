package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by makis on 12/1/2018.
 */
public class SellerDiscountTest {

    SellerDiscount sellerDiscount;
    @Before
    public void setUp() throws Exception {
        sellerDiscount=new SellerDiscount(1,10,85.70,60.70,"laptops","image");
    }

    @Test
    public void getId() throws Exception {
        assertEquals(0,sellerDiscount.getId());
    }

    @Test
    public void getCategory() throws Exception {
        assertEquals(10,sellerDiscount.getCategory());
    }

    @Test
    public void getCurrentPrice() throws Exception {
        assertEquals("60.7",Double.toString(sellerDiscount.getCurrentPrice()));
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("laptops",sellerDiscount.getDescription());

    }

    @Test
    public void getImage() throws Exception {
        assertEquals("image",sellerDiscount.getImage());
    }

    @Test
    public void getDiscountPercent() throws Exception {
        assertEquals("29.171528588098017",Double.toString(sellerDiscount.getDiscountPercent()));
    }

}