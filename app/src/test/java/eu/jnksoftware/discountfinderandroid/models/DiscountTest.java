package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sotiris on 26/10/2017.
 */
public class DiscountTest {

    @Test
    public void isValidPrices() throws Exception {
        Discount test;
        test = new Discount("pcs",55,45,1,"photoUrl");
        assertTrue(test.isValidPrices(55,45));
    }
    @Test
    public void notValidPrice() throws Exception {
        try {
            new Discount("pcs",-55,45,1,"photoUrl");
        } catch (Exception ex) {
            assertTrue(
                    ex.getMessage().equals("only positive prices accepted")
            );
        }
    }
    @Test
    public void isDiscountPriceBigger() throws Exception {
        try {
            new Discount("pcs",  40.5, 45, 1, "photoUrl");
        } catch (Exception ex) {
            assertTrue(
                    ex.getMessage().equals("Your discount price is bigger than original")
            );

        }
    }

    @Test
    public void isDiscountPriceLower() throws Exception {
        Discount test;
        test = new Discount("pcs",55.5,45,1,"photoUrl");
        assertTrue(test.isDiscountPriceLower());

    }

}