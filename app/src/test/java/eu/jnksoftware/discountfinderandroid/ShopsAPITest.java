package eu.jnksoftware.discountfinderandroid;

import org.junit.Test;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.api.APIConfig;
import eu.jnksoftware.discountfinderandroid.api.ShopsAPI;
import eu.jnksoftware.discountfinderandroid.models.Shop;

import static org.junit.Assert.assertEquals;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 22/10/2017
 * License: Apache License 2.0
 */
public class ShopsAPITest {
    @Test
    public void simpleTest() throws Exception {
        ShopsAPI test = new ShopsAPI(APIConfig.APILinkTest + "/shop/");
        List<Shop> list = test.getList();

        assertEquals("MammasPizza", list.get(0).shopName);
        assertEquals("TEI CAFE", list.get(1).shopName);
    }
}