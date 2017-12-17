package eu.jnksoftware.discountfinderandroid.Apis;


import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;

import static org.junit.Assert.assertEquals;

public class TestUserShopsDiscountService {
    ShopsApiInterface service = ApiUtils.getMockUserServiceShopsApi();

    @Test
    public void getSellerDiscountsTest() throws IOException {

        List<SellerDiscount> discounts = new ArrayList<>();
        String auth="Bearer mock_access";

        discounts = service.getSellerDiscounts(auth).execute().body();

        assertEquals(6,discounts.size());
        assertEquals("pizza margarita",discounts.get(2).getDescription());
    }


}
