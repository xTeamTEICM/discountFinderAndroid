package Apis;

/**
 * Created by nikos on 10/12/2017.
 */

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;

public class TestUserPreferences extends TestCase{
        @Test
        public void testPostuserId() throws IOException

        {
            IuserService s = ApiUtils.getUserMockService();
            DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
            discountPreferencesRequest.setPrice("50");
            discountPreferencesRequest.setCategory("1");
            discountPreferencesRequest.setTags("Sample,Demo,App,Data");
            String auth="Bearer mock_access";
            DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
            assertEquals("1", discountPreferencesPostResponse.getUserid().toString());
        }
    @Test
    public void testPostCategory() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setTags("Sample,Demo,App,Data");
        String auth="Bearer mock_access";
        DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
        assertEquals("1", discountPreferencesPostResponse.getCategory().toString());
    }
    @Test
    public void testPostPrice() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setTags("Sample,Demo,App,Data");
        String auth="Bearer mock_access";
        DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
        assertEquals("50", discountPreferencesPostResponse.getPrice().toString());
    }
    @Test
    public void testPostTags() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setTags("Sample,Demo,App,Data");
        String auth="Bearer mock_access";
        DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
        assertEquals("Sample,Demo,App,Data", discountPreferencesPostResponse.getTags().toString());
    }
    @Test
    public void testGetid() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
       DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals(37, discountPreferencesResponse.getId().toString());
    }
    @Test
    public void testGetCategory() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals(1, discountPreferencesResponse.getCategory().toString());
    }
    @Test
    public void testGetUserid() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals(30, discountPreferencesResponse.getUserId().toString());
    }
    @Test
    public void testGetPrice() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals(50, discountPreferencesResponse.getPrice().toString());
    }

    @Test
    public void testGetTag() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals("Sample, Demo, App, Data", discountPreferencesResponse.getTags().toString());
    }

    @Test
    public void testGetImage() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals("http://img.youtube.com/", discountPreferencesResponse.getImage().toString());
    }
    @Test
    public void testGetCategoryTitle() throws IOException

    {
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        DiscountPreferencesResponse discountPreferencesResponse= (DiscountPreferencesResponse) s.getDiscountsPreference(auth).execute().body();
        assertEquals("Computer", discountPreferencesResponse.getCategoryTitle().toString());
    }

    @Test
    public void testPutcategory() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("1", discountPreferencesResponse.getCategory().toString());
    }
    @Test
    public void testPutPrice() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("50", discountPreferencesResponse.getPrice().toString());
    }
    @Test
    public void testPutTags() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getUserMockService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("Demo,App,Data", discountPreferencesResponse.getTags().toString());
    }





}
