package eu.jnksoftware.discountfinderandroid.Apis;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

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


}