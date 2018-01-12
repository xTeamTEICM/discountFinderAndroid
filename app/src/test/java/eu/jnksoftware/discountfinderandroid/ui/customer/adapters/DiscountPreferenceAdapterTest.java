package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;

import android.content.Context;
import android.test.mock.MockContext;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikos on 12/1/2018.
 */
public class DiscountPreferenceAdapterTest {
    private List<DiscountPreferencesResponse> discountPreferencesResponses;
    private Context mContext=new MockContext();
    DiscountPreferenceAdapter adapter;

    @Before
    public void setUp() throws Exception {
        List<DiscountPreferencesResponse> preferencesResponses=new ArrayList<>();
         Collections.addAll(preferencesResponses,new DiscountPreferencesResponse(1,1,1,40,"tag1","shoe")
                ,new DiscountPreferencesResponse(2,2,2,30,"tag2","coffe")
                ,new DiscountPreferencesResponse(3,3,3,20,"tag3","pc"));

        adapter = new DiscountPreferenceAdapter(preferencesResponses,mContext,"auth");
    }



    @Test
    public void removeDiscount() throws Exception {
        adapter.removeDiscount(2);
        assertThat(adapter.getDiscountPreferencesResponses().size(),is(2));
    }

    @Test
    public void restoreDiscount() throws Exception {
        final int deletePosition = 2;
        final  DiscountPreferencesResponse deletedDiscount = adapter.getDiscountPreferencesResponses().get(deletePosition);

        adapter.removeDiscount(deletePosition);
        adapter.restoreDiscount(deletedDiscount,deletePosition);

        assertThat(adapter.getDiscountPreferencesResponses().get(2),is(deletedDiscount));

    }

    @Test
    public void getDiscountId() throws Exception {
        final int idpref=adapter.getDiscountId(0);
        Assert.assertEquals(1,idpref);
    }

    @Test
    public void getItemCount() throws Exception {
        final int preferences=adapter.getItemCount();
        Assert.assertEquals(3,preferences);
    }

}