package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by poz on 4/11/2017.
 */
public class LocationTest {
    @Test
    public void getLatitude() throws Exception {
        Location test = new Location(50,50);
        assertEquals(50,test.getLatitude());
    }

    @Test
    public void setLatitude() throws Exception {
        Location test = new Location(50,50);
        test.setLatitude(100);
        assertEquals(100,test.getLongitude());
    }

    @Test
    public void getLongitude() throws Exception {
        Location test = new Location(50,50);
        assertEquals(50,test.getLongitude());
    }

    @Test
    public void setLongitude() throws Exception {
        Location test = new Location(50,50);
        test.setLongitude(100);
        assertEquals(100,test.getLongitude());
    }

}