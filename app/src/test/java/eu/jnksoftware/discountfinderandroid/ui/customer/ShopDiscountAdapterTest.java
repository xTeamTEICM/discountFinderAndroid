package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShopDiscountAdapterTest {
    List<SellerDiscount> discounts = new ArrayList<>();
    ShopDiscountAdapter adapter;
    Context mockContext = new MockContext();

    @Before
    public void setUp() throws Exception {
        List<SellerDiscount> givenDiscounts = new ArrayList<>();
        Collections.addAll(givenDiscounts,new SellerDiscount(1,1,30.0
                        ,25.0,"ds","dsds")
                ,new SellerDiscount(2,2,54.0,36.0,"ds","dsa")
                ,new SellerDiscount(3,1,104.4,32.5,"a","b"));

        adapter = new ShopDiscountAdapter(mockContext,givenDiscounts);
    }

    @Test
    public void removeDiscountTest(){
        adapter.removeDiscount(2);
        assertThat(adapter.discounts.size(),is(2));
    }

    @Test
    public void restoreDiscountTest(){
        final int deletePosition = 2;
        final SellerDiscount deletedDiscount = adapter.discounts.get(deletePosition);

        adapter.removeDiscount(deletePosition);
        adapter.restoreDiscount(deletedDiscount,deletePosition);

        assertThat(adapter.discounts.get(2),is(deletedDiscount));
    }


}