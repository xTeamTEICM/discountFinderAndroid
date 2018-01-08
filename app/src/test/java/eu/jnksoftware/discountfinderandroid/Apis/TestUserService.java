package eu.jnksoftware.discountfinderandroid.Apis;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.services.MockUserService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
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
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("140");
        discountPreferencesRequest.setCategory("shoes");
        discountPreferencesRequest.setTags("tag");
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<DiscountPreferencesResponse> putPreference=mockService.putDiscountPreferences(1,discountPreferencesRequest,"auth");
        retrofit2.Response<DiscountPreferencesResponse> putPreferenceResponse=putPreference.execute();
        Assert.assertEquals("1",putPreferenceResponse.body().getCategory().toString());
        Assert.assertEquals("1",putPreferenceResponse.body().getId().toString());

        Assert.assertEquals("tag",putPreferenceResponse.body().getTags());
    }

    @Test
    public void testPostPreferences() throws Exception{
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("140");
        discountPreferencesRequest.setCategory("shoes");
        discountPreferencesRequest.setTags("tag");
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<DiscountPreferencesPostResponse> postPreference=mockService.postDiscountPreferences(discountPreferencesRequest,"auth");
        retrofit2.Response<DiscountPreferencesPostResponse> postPreferenceResponse=postPreference.execute();
        Assert.assertEquals("shoes",postPreferenceResponse.body().getCategory());
        Assert.assertEquals("1",postPreferenceResponse.body().getId().toString());
        Assert.assertEquals("40",postPreferenceResponse.body().getPrice());
        Assert.assertEquals("tag",postPreferenceResponse.body().getTags());
    }


}