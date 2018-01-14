package eu.jnksoftware.discountfinderandroid;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.UpdatePostShop;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountPost;
import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.services.MockUserService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class TestUserService extends TestCase {
        Retrofit retrofit;
        MockRetrofit mockRetrofit;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }
    @Test
    public void testLogin() throws Exception{

        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<User> caluser=mockService.login("nikos","123456");
        retrofit2.Response<User> userResponse=caluser.execute();

        Assert.assertEquals("access",userResponse.body().getAccessToken());
        Assert.assertEquals("Bearer",userResponse.body().getTokenType());
        Assert.assertEquals("5100",userResponse.body().getRefreshToken());
        Assert.assertEquals("5100",userResponse.body().getExpiresIn().toString());
    }
    @Test
    public void testRegister() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<User> doregister=mockService.register("nikos","delis","nikos@example.com","123455");
        retrofit2.Response<User> registerResponse=doregister.execute();
        Assert.assertEquals("access",registerResponse.body().getAccessToken());
        Assert.assertEquals("refresh",registerResponse.body().getRefreshToken());
        Assert.assertEquals("Bearer",registerResponse.body().getTokenType());
        Assert.assertEquals("5000",registerResponse.body().getExpiresIn().toString());
    }
    @Test
    public void testPutPreferences() throws Exception{

        String category="shoes";
        String price="140";
        String tags="tag";
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<DiscountPreferencesResponse> putPreference=mockService.putDiscountPreferences(1,category,price,tags,"auth");
        retrofit2.Response<DiscountPreferencesResponse> putPreferenceResponse=putPreference.execute();
        Assert.assertEquals("1",putPreferenceResponse.body().getCategory().toString());
        Assert.assertEquals("1",putPreferenceResponse.body().getId().toString());
        Assert.assertEquals("40.0",Float.toString(putPreferenceResponse.body().getPrice()));
        Assert.assertEquals("tag",putPreferenceResponse.body().getTags());
    }

    @Test
    public void testPostPreferences() throws Exception{
        String category="shoes";
        String price="40";
        String tags="tag";
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<DiscountPreferencesPostResponse> postPreference=mockService.postDiscountPreferences(category,price,tags,"auth");
        retrofit2.Response<DiscountPreferencesPostResponse> postPreferenceResponse=postPreference.execute();
        Assert.assertEquals("shoes",postPreferenceResponse.body().getCategory());
        Assert.assertEquals("1",postPreferenceResponse.body().getId().toString());
        Assert.assertEquals("40",postPreferenceResponse.body().getPrice());
        Assert.assertEquals("tag",postPreferenceResponse.body().getTags());
    }

    @Test
    public void testgetPreferences() throws Exception{

        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<List<DiscountPreferencesResponse>> getPreference=mockService.getDiscountsPreference("auth");
        Response<List<DiscountPreferencesResponse>> getPreferenceResponse=getPreference.execute();
        Assert.assertEquals("tag1",getPreferenceResponse.body().get(0).getTags());
        Assert.assertEquals("1",getPreferenceResponse.body().get(0).getId().toString());
        Assert.assertEquals("1",getPreferenceResponse.body().get(0).getCategory().toString());
        Assert.assertEquals("1",getPreferenceResponse.body().get(0).getUserId().toString());
        Assert.assertEquals("shoe",getPreferenceResponse.body().get(0).getCategoryTitle());
        Assert.assertEquals("40.0",Float.toString(getPreferenceResponse.body().get(0).getPrice()));

    }

    @Test
    public  void testGetDiscounts() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<List<Discount>> testDiscount=mockService.getDiscounts(1000,"auth");
        Response<List<Discount>> getDiscounts =testDiscount.execute();
        Assert.assertEquals(1,getDiscounts.body().get(0).getDiscountId());
        Assert.assertEquals("food",getDiscounts.body().get(0).getCategory());
        Assert.assertEquals("food",getDiscounts.body().get(0).getShortDescription());
        Assert.assertEquals("Pizza",getDiscounts.body().get(0).getShopName());
        Assert.assertEquals("image",getDiscounts.body().get(0).getProductImageURL());
        Assert.assertEquals(43.27,getDiscounts.body().get(0).getShopLatPos());
        Assert.assertEquals(23.88,getDiscounts.body().get(0).getShopLogPos());

    }

    @Test
    public void testDeleteDiscountPreference() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<Void> call = mockService.deleteDiscountPreference(1,"auth");
        retrofit2.Response<Void> deletePreferenceResponse=call.execute();
        Assert.assertEquals(200, deletePreferenceResponse.code());


    }
    @Test
    public void testFetchCategories() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<List<Category>> categoryCall=mockService.fetchCategories();
        Response<List<Category>> getCategory=categoryCall.execute();
        Assert.assertEquals("1",getCategory.body().get(0).getId());
        Assert.assertEquals("title1",getCategory.body().get(0).getTitle());
    }

    @Test
    public void testTopDiscount() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<List<TopDiscount>> topDiscountCall=mockService.getTopDiscounts(1,"auth");
        Response<List<TopDiscount>> getTopDiscount=topDiscountCall.execute();
        Assert.assertEquals("short",getTopDiscount.body().get(0).getShortDescription());
        Assert.assertEquals("image1",getTopDiscount.body().get(0).getProductImage());
    }

    @Test
    public void testDeleteShop() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<Void> deleteShopCall=mockService.deleteShop(1,"auth");
        retrofit2.Response<Void> deleteShopPreference=deleteShopCall.execute();
        Assert.assertEquals(200,deleteShopPreference.code());
    }
    @Test
    public void testgetUserShop()throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<List<Shop>> getUserShopCall=mockService.getUserShops("auth");
        Response<List<Shop>> getUserShopCallPreference=getUserShopCall.execute();
        Assert.assertEquals(1,getUserShopCallPreference.body().get(0).getId());
        Assert.assertEquals("brand1",getUserShopCallPreference.body().get(0).getBrandName());
        Assert.assertEquals(1,getUserShopCallPreference.body().get(0).getOwnerId());
        Assert.assertEquals("12.0",Double.toString(getUserShopCallPreference.body().get(0).getLocation().getLatPos()));
        Assert.assertEquals("10.0",Double.toString(getUserShopCallPreference.body().get(0).getLocation().getLogPos()));

    }
    @Test
    public void testaddShop()throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        PostShop postShop=new PostShop("brand",10,10);
        Call<Void> calladdShop = mockService.addShop(postShop,"auth");
        retrofit2.Response<Void> addShopeResponse=calladdShop.execute();
        Assert.assertEquals(200,addShopeResponse.code());

    }

    @Test
    public void testupdateShop()throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        UpdatePostShop updatepostShop=new UpdatePostShop(1,"brand",1,1);
        Call<Void> callupdateShop = mockService.updateShop(updatepostShop,"auth");
        retrofit2.Response<Void> addShopeResponse=callupdateShop.execute();
        Assert.assertEquals(200,addShopeResponse.code());

    }
    @Test
    public void testAddDiscount() throws IOException {
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        DiscountPost discountPost=new DiscountPost();
        discountPost.setCategory(1);
        discountPost.setDescription("desc");
        discountPost.setOriginalPrice(19);
        discountPost.setCategory(1);
        discountPost.setCurrentPrice(3);
        discountPost.setShopId(1);
        discountPost.setImageTitle("image");
        discountPost.setImageBase("image");
        Call<DiscountGet> calladddiscount = mockService.addDiscount(discountPost,"auth");
        retrofit2.Response<DiscountGet> addShopeResponse=calladddiscount.execute();
        Assert.assertEquals(1,addShopeResponse.body().getCategory());
        Assert.assertEquals(20,addShopeResponse.body().getCurrentPrice());
        Assert.assertEquals(1,addShopeResponse.body().getId());
        Assert.assertEquals("desc",addShopeResponse.body().getDescription());
        Assert.assertEquals("image",addShopeResponse.body().getImage());
        Assert.assertEquals(40,addShopeResponse.body().getOriginalPrice());
        Assert.assertEquals(1,addShopeResponse.body().getShopId());


    }

    @Test
    public void testGetSellerDiscount() throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<List<SellerDiscount>> getSellerDiscountCall=mockService.getSellerDiscounts(1,"auth");
        Response<List<SellerDiscount>> getSellerDiscountCallPreference=getSellerDiscountCall.execute();
        Assert.assertEquals(1,getSellerDiscountCallPreference.body().get(0).getCategory());
        Assert.assertEquals("40.0",Double.toString(getSellerDiscountCallPreference.body().get(0).getCurrentPrice()));
        Assert.assertEquals("30.0",Double.toString(getSellerDiscountCallPreference.body().get(0).getOriginalPrice()));
        Assert.assertEquals(1,getSellerDiscountCallPreference.body().get(0).getShopId());
        Assert.assertEquals("desc",getSellerDiscountCallPreference.body().get(0).getDescription());
        Assert.assertEquals("image",getSellerDiscountCallPreference.body().get(0).getImage());

    }
    @Test
    public void testdeleteSellerDiscount()throws Exception{
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<Void> calldeleteeShop = mockService.deleteSellerDiscount(1,"auth");
        retrofit2.Response<Void> adddeleteSellerPreference=calldeleteeShop.execute();
        Assert.assertEquals(200,adddeleteSellerPreference.code());

    }



}