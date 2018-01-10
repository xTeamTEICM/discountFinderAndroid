package eu.jnksoftware.discountfinderandroid.ui.customer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;


public class ViewStoreTest {
//
//    IuserService mockApiInterface = Mockito.mock(IuserService.class);
//    final Call<List<SellerDiscount>> mockCall = Mockito.mock(Call.class);
//    List<SellerDiscount> mockCallDiscounts = new ArrayList<>();
//    List<SellerDiscount> discounts = new ArrayList<>();
//
//    @Before
//    public void setUp() throws Exception {
//        Collections.addAll(mockCallDiscounts,new SellerDiscount(1,1,30.0
//                        ,25.0,"ds","dsds")
//                ,new SellerDiscount(2,2,54.0,36.0,"ds","dsa")
//                ,new SellerDiscount(3,1,104.4,32.5,"a","b"));
//    }
//
//    @Test
//    public void getSellerDiscountsTest() {
//
//        Mockito.when(mockApiInterface.getSellerDiscounts(1,"")).thenReturn(mockCall);
//
//        Mockito.doAnswer(new Answer() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                Callback<List<SellerDiscount>> callback = invocation.getArgument(0);
//
//                callback.onResponse(mockCall, Response.success(mockCallDiscounts));
//                return null;
//            }
//        }).when(mockCall).enqueue(any(Callback.class));
//
//        Call<List<SellerDiscount>> call = mockApiInterface.getSellerDiscounts(1,"");
//        call.enqueue(new Callback<List<SellerDiscount>>() {
//            @Override
//            public void onResponse(Call<List<SellerDiscount>> call, Response<List<SellerDiscount>> response) {
//                discounts = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<SellerDiscount>> call, Throwable t) {
//
//            }
//        });
//
//        assertEquals(mockCallDiscounts,discounts);
//    }


}