package eu.jnksoftware.discountfinderandroid.services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jordankostelidis on 10/11/2017.
 */
public class GETNetworkTest {

    @Test
    public void testBasicCall() throws Exception {
        GETNetwork test = new GETNetwork("http://localhost/iordkost/testing/get.php");
        test.addHeader("method", "get");
        test.addHeader("token", "asdfghjkl");
        test.addHeader("id", "1");

        test.addProperty("name", "lalakisShop");

        test.call();
        System.out.println(test.getResult());
    }

}