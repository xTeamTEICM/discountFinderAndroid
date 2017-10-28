package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * Created by poz on 28/10/2017.
 *
 */

public class DiscountRequestTest {

    @Test
    public void isValidCategory() throws Exception {
        DiscountRequest test;
        test = new DiscountRequest(1,"Υπολογιστές",150,"Μητρικές");
        assertTrue(test.isAvailable("Υπολογιστές"));
    }

    @Test
    public void isNotValidCategory() throws Exception {
        DiscountRequest test;
        test = new DiscountRequest(1,"Υπολογιστές",150,"Μητρικές");
        assertFalse(test.isAvailable("Αεροπλάνα"));
    }

    }

