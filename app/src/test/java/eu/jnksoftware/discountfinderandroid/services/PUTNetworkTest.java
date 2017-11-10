package eu.jnksoftware.discountfinderandroid.services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jordankostelidis on 10/11/2017.
 */
public class PUTNetworkTest {

    @Test
    public void testBasicCall() throws Exception {
        PUTNetwork test = new PUTNetwork("http://localhost/iordkost/testing/put.php");
        test.addHeader("method", "put");
        test.addHeader("token", "asdfghjkl");
        test.addHeader("id", "1");

        test.addProperty("name", "lalakisShop");

        test.call();
        System.out.println(test.getResult());
    }

}