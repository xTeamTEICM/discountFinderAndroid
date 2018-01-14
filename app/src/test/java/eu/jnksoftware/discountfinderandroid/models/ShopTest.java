package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by makis on 12/1/2018.
 */
public class ShopTest {
    Shop shop;

    @Before
    public void setUp() throws Exception {
        shop=new Shop(1,10,"captain",new Location(80.66,45.55));
    }

    @Test
    public void getId() throws Exception {
        assertEquals(1,shop.getId());
    }

    @Test
    public void getBrandName() throws Exception {
        assertEquals("captain",shop.getBrandName());
    }

    @Test
    public void setName() throws Exception {
        shop.setName("mamaspizza");
        assertEquals("mamaspizza",shop.getBrandName());
    }

    @Test
    public void getLocation() throws Exception {
        assertEquals("80.66",Double.toString(shop.getLocation().getLogPos()));
        assertEquals("45.55",Double.toString(shop.getLocation().getLatPos()));

    }

}