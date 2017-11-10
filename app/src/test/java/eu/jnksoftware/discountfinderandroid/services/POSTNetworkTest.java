package eu.jnksoftware.discountfinderandroid.services;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 20/10/2017
 * License: Apache License 2.0
 */
public class POSTNetworkTest {

    @Test
    public void RequestInvalidUrl() throws Exception {
        try {
            new POSTNetwork("https://aVoidSiteSampleForTesting");
        } catch (Exception ex) {
            assertTrue(
                    ex.getMessage().equals("Invalid URL")
            );
        }
    }

    @Test
    public void RequestNotSafeUrl() throws Exception {
        try {
            new POSTNetwork("http://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        } catch (Exception ex) {
            assertTrue(
                    ex.getMessage().equals("URL must have SSL (HTTPS)")
            );
        }
    }

    @Test
    public void RequestWithoutProperties() throws Exception {
        POSTNetwork test = new POSTNetwork("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.call()
        );
        assertTrue(
                test.getResult().contains("INVALID_REQUEST")
        );
    }

    @Test
    public void RequestWithProperties() throws Exception {
        POSTNetwork test = new POSTNetwork("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.addProperty("eMail", "iordkost@teicm.gr")
        );
        assertTrue(
                test.addProperty("password", "myPassword")
        );
        assertTrue(
                test.call()
        );
        assertTrue(
                test.getResult().contains("INVALID_FIELDS")
        );
    }

    @Test
    public void PropertyUpdateNotExist() throws Exception {
        POSTNetwork test = new POSTNetwork("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                !test.updateProperty("password", "helloWorld")
        );
    }

    @Test
    public void PropertyUpdateExist() throws Exception {
        POSTNetwork test = new POSTNetwork("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.addProperty("password", "myPassword")
        );
        assertTrue(
                test.updateProperty("password", "helloWorld")
        );

    }

    @Test
    public void PropertyDeleteNotExist() throws Exception {
        POSTNetwork test = new POSTNetwork("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                !test.removeProperty("password")
        );
    }

    @Test
    public void PropertyDeleteExist() throws Exception {
        POSTNetwork test = new POSTNetwork("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.addProperty("password", "myPassword")
        );
        assertTrue(
                test.removeProperty("password")
        );
    }

    @Test
    public void testBasicCall() throws Exception {
        POSTNetwork test = new POSTNetwork("https://localhost/iordkost/testing/post.php");
        test.addHeader("method", "post");
        test.addHeader("token", "asdfghjkl");
        test.addHeader("id", "1");

        test.addProperty("name", "lalakisShop");

        test.call();
        System.out.println(test.getResult());
    }

}