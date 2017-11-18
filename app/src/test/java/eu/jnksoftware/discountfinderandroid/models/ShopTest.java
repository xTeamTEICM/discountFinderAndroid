package eu.jnksoftware.discountfinderandroid.models;

import android.net.Uri;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by nikos on 18/11/2017.
 */

public class ShopTest {
    @Test
    public void getid() throws Exception {
        Location test = new Location(50, 50);
        Shop shop = new Shop(1, "FirstShop", 2, test);
        assertEquals(1,shop.getId());
    }
    @Test
    public void getName() throws  Exception{
        Location test = new Location(50, 50);
        Shop shop=new Shop(1,"FirstShop",2,test);
        assertEquals("FirstShop",shop.getName());
    }
    @Test
    public void setName() throws Exception{
        Location test = new Location(50, 50);
        Shop shop=new Shop(1,"FirstShop",2,test);
        shop.setName("SecondShop");
        assertEquals("SecondShop",shop.getName());
    }
    @Test
    public void getOwnderId() throws Exception{
        Location test = new Location(50, 50);
        Shop shop=new Shop(1,"FirstShop",2,test);
        assertEquals(2,shop.getOwnerId());
    }
/*@Test
    public void getMapsUri()throws Exception{
    Location test = new Location(50, 50);
    Shop shop=new Shop(1,"FirstShop",2,test);
    String labelLocation="x-Team Sample : " + shop.getOwnerId();
    Uri apotelesma=shop.getMapsUri();
    assertEquals("geo:" +
            "<" + 50 + ">,<" + 50 + ">" +
            "?q=<" + 50 + ">,<" + 50 + ">(" + labelLocation + ")",apotelesma);
}*/
}