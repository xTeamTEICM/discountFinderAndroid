package eu.jnksoftware.discountfinderandroid.ui.customer.adapters;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.Shop;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikos on 11/1/2018.
 */
public class RecyclerAdapterTest {
    private List<Shop> shopList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    Context mContext=new MockContext();

    @Before
    public void setUp() throws Exception {
        List<Shop> givenShop = new ArrayList<>();
        Location location=new Location(15,10);

        Collections.addAll(givenShop,new Shop(1,1
                        ,"Shop1",location)
                ,new Shop(2,2,"Shop2",location)
                ,new Shop(3,3,"Shop3",location));

        recyclerAdapter = new RecyclerAdapter(givenShop,mContext,"auth");
    }

    @Test
    public void ItemCount(){
        Assert.assertEquals(3,recyclerAdapter.getItemCount());
    }

}