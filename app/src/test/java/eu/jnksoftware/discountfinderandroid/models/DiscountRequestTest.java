package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.api.APIConfig;
import eu.jnksoftware.discountfinderandroid.api.CategoryAPI;
import eu.jnksoftware.discountfinderandroid.services.POSTNetwork;

import static org.junit.Assert.*;

/**
 *
 * Created by poz on 29/10/2017.
 *
 */
public class DiscountRequestTest {
    @Test
    public void getId() throws Exception {
        Category categoryTest = new Category(1,"test");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        assertEquals(1, test.getId());
    }

    @Test
    public void getCategory() throws Exception {
        Category categoryTest = new Category(1,"test");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        assertEquals("test", test.getCategory());
    }

    @Test
    public void setCategory() throws Exception {
        Category categoryTest = new Category(1,"test");
        categoryTest.setTitle("Computers");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        assertEquals("Computers", test.getCategory());
    }

    @Test
    public void getPrice() throws Exception {
        Category categoryTest = new Category(1,"test");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        assertEquals("150", test.getPrice());
    }

    @Test
    public void setPrice() throws Exception {
        Category categoryTest = new Category(1,"test");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        test.setPrice(150);
        assertEquals("150", test.getPrice());
    }

    @Test
    public void getTags() throws Exception {
        Category categoryTest = new Category(1,"test");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        assertEquals("motherboard", test.getPrice());
    }

    @Test
    public void setTags() throws Exception {
        Category categoryTest = new Category(1,"test");
        DiscountRequest test = new DiscountRequest(1,categoryTest,150,"motherboards");
        test.setTags("motherboards");
        assertEquals("motherboards",test.getTags());
    }


}