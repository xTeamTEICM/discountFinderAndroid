package eu.jnksoftware.discountfinderandroid.Apis;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.models.token.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.services.MockUserService;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
        UserTokenRequest userTokenRequest=new UserTokenRequest();
        userTokenRequest.setUsername("Nikos");
        userTokenRequest.setPassword("123456");
        BehaviorDelegate<IuserService>delegate=mockRetrofit.create(IuserService.class);
        IuserService mockService=new MockUserService(delegate);
        Call<User> caluser=mockService.getTokenAccess(userTokenRequest);
        retrofit2.Response<User> userResponse=caluser.execute();

        Assert.assertEquals("access",userResponse.body().getAccessToken());
        Assert.assertEquals("Bearer",userResponse.body().getTokenType());
        Assert.assertEquals("5100",userResponse.body().getRefreshToken());
        Assert.assertEquals("5100",userResponse.body().getExpiresIn().toString());
    }


}