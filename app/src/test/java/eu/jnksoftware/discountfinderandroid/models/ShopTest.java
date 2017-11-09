package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 *
 * Created by poz on 4/11/2017.
 *
 */

public class ShopTest {
    @Test
    public void getMapsUri() throws Exception {
        Location testLocation = new Location(50,50);
        Shop test = new Shop(1,"owner",testLocation);
        assertEquals("geo:" +
                "<" + 50 + ">,<" + 50 + ">" +
                "?q=<" + 50 + ">,<" + 50 + ">(" + "test" + ")",test.getMapsUri());
    }

    @Test
    public void getId() throws Exception {
        Location testLocation = new Location(50,50);
        Shop test = new Shop(1,"owner",testLocation);
        assertEquals(1,test.getId());
    }

    @Test
    public void getOwnerId() throws Exception {
        Location testLocation = new Location(50,50);
        Shop test = new Shop(1,"owner",testLocation);
        assertEquals("owner",test.getOwnerId());
    }

    @Test
    public void getLocation() throws Exception {
        Location testLocation = new Location(50,50);
        Shop test = new Shop(1,"owner",testLocation);
        assertEquals(testLocation,test.getLocation());
    }

}