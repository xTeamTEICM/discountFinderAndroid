package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;

import static org.junit.Assert.assertEquals;


public class TopDiscountAdapterTest {
    List<TopDiscount> topDiscountList;
    Context mContext;
    TopDiscountAdapter adapter;
    @Before
    public void setUp() throws Exception {
        mContext= new MockContext();
        topDiscountList = new ArrayList<>();
        Collections.addAll(topDiscountList,new TopDiscount("a","b"),
                new TopDiscount("ab","bb"),
                        new TopDiscount("sad","asd"),
                        new TopDiscount("dsadsa","dsadsa"));
        adapter = new TopDiscountAdapter(mContext,topDiscountList);
    }

    @Test
    public void getTopDiscountList() throws Exception {
        List<TopDiscount> tempDiscountList;
        tempDiscountList = adapter.getTopDiscountList();
        assertEquals(tempDiscountList,topDiscountList);
    }

    @Test
    public void getItemCount() throws Exception {
        int testSize = adapter.getItemCount();
        assertEquals(testSize,topDiscountList.size());
    }

}