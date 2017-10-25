package eu.jnksoftware.discountfinderandroid;

import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.services.Network;

import static org.junit.Assert.assertTrue;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 20/10/2017
 * License: Apache License 2.0
 */
public class NetworkTest {

    @Test
    public void RequestInvalidUrl() throws Exception {
        try {
            new Network("https://aVoidSiteSampleForTesting");
        } catch (Exception ex) {
            assertTrue(
                    ex.getMessage().equals("Invalid URL")
            );
        }
    }

    @Test
    public void RequestNotSafeUrl() throws Exception {
        try {
            new Network("http://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        } catch (Exception ex) {
            assertTrue(
                    ex.getMessage().equals("URL must have SSL (HTTPS)")
            );
        }
    }

    @Test
    public void RequestWithoutProperties() throws Exception {
        Network test = new Network("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.call()
        );
        assertTrue(
                test.getResult().contains("INVALID_REQUEST")
        );
    }

    @Test
    public void RequestWithProperties() throws Exception {
        Network test = new Network("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
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
        Network test = new Network("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                !test.updateProperty("password", "helloWorld")
        );
    }

    @Test
    public void PropertyUpdateExist() throws Exception {
        Network test = new Network("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.addProperty("password", "myPassword")
        );
        assertTrue(
                test.updateProperty("password", "helloWorld")
        );

    }

    @Test
    public void PropertyDeleteNotExist() throws Exception {
        Network test = new Network("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                !test.removeProperty("password")
        );
    }

    @Test
    public void PropertyDeleteExist() throws Exception {
        Network test = new Network("https://ouranos.jnksoftware.eu/pointSystemAPI/v1/auth/login/");
        assertTrue(
                test.addProperty("password", "myPassword")
        );
        assertTrue(
                test.removeProperty("password")
        );
    }

}