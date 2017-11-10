package eu.jnksoftware.discountfinderandroid.services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jordankostelidis on 10/11/2017.
 */
public class DELETENetworkTest {

    @Test
    public void testBasicCall() throws Exception {
        DELETENetwork test = new DELETENetwork("https://localhost/iordkost/testing/delete.php");
        test.addHeader("method", "delete");
        test.addHeader("token", "asdfghjkl");
        test.addHeader("id", "1");

        test.addProperty("name", "lalakisShop");

        test.call();
        System.out.println(test.getResult());
    }

}