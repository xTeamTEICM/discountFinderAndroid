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
        assertEquals(50,test.getLogPos(),1);
    }

    @Test
    public void setLatitude() throws Exception {
        Location test = new Location(50,50);
        test.setLatPos(100);
        assertEquals(100,test.getLatPos(),1);
    }

    @Test
    public void getLongitude() throws Exception {
        Location test = new Location(50,50);
        assertEquals(50,test.getLogPos(),1);
    }

    @Test
    public void setLongitude() throws Exception {
        Location test = new Location(50,50);
        test.setLogPos(100);
        assertEquals(100,test.getLogPos(),1);
    }

}