package eu.jnksoftware.discountfinderandroid.Apis;


import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.services.IuserService;

import static org.junit.Assert.assertEquals;

public class TestUserShopsDiscountService {
  /*  IuserService service = ApiUtils.getMockUserService();
    String auth="Bearer mock_access";

    @Test
    public void getSellerDiscountsTest() throws IOException {

        List<SellerDiscount> discounts = new ArrayList<>();

        discounts = service.getSellerDiscounts(auth).execute().body();

        assertEquals(6,discounts.size());
        assertEquals("pizza margarita",discounts.get(2).getDescription());
    }

    @Test
    public void deleteSellerDiscountTest() throws IOException {
        int pos = 3;
        service.deleteSellerDiscount(3,auth).execute();
    }

*/
}
