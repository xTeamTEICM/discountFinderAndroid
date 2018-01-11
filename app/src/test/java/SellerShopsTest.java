import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.ui.customer.shops.SellerShops;

import static org.junit.Assert.*;

/**
 * Created by dito on 31/12/2017.
 */
public class SellerShopsTest {
    SellerShops sellerShops = new SellerShops();

    @Before
    public void setUp() throws Exception {
      sellerShops.setApiService(ApiUtils.getUserService());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserShopsTestNullAuth() throws Exception{
        sellerShops.setAuth(null);
        sellerShops.getUserShops();
        assertTrue(sellerShops.getShops()!=null);
        assertEquals(0,sellerShops.getShops().size());
    }

}