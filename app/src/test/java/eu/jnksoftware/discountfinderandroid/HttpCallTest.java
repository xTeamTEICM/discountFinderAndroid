package eu.jnksoftware.discountfinderandroid;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
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


import static org.junit.Assert.assertEquals;


/**
 * Created by makis on 11/1/2018.
 */
public class HttpCallTest extends TestCase {
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
    public void testFcmToken() throws Exception {

        BehaviorDelegate<IuserService> delegate = mockRetrofit.create(IuserService.class);
        IuserService mockService = new MockUserService(delegate);
        FcmToken token = new FcmToken("fcmtoken");
        String auth = "Bearer_mock";
        Call<Void> call = mockService.registerFcmToken(token, auth);
        retrofit2.Response<Void> response = call.execute();

        Assert.assertEquals(200, response.code());

    }

    @Test
    public void testDeviceLocation() throws Exception {

        BehaviorDelegate<IuserService> delegate = mockRetrofit.create(IuserService.class);
        IuserService mockService = new MockUserService(delegate);
        Location location = new Location(47.477, 77.444);
        String auth = "Bearer_mock";
        Call<Void> call = mockService.setUserLocation(location, auth);
        retrofit2.Response<Void> responseTestDeviceLocation = call.execute();

        Assert.assertEquals(200, responseTestDeviceLocation.code());

    }

    @Test
    public void testRefreshToken() throws Exception {

        BehaviorDelegate<IuserService> delegate = mockRetrofit.create(IuserService.class);
        IuserService mockService = new MockUserService(delegate);
        Call<User> call = mockService.refreshAccessToken("refresh_token");
        retrofit2.Response<User> responseTestDeviceLocation = call.execute();

        Assert.assertEquals(200, responseTestDeviceLocation.code());
    }





}

/* @Test
    public void setFcmToken() throws Exception {
        MockWebServer server;
        server = new MockWebServer();


        server.start();
        ApiUtils.baseUrl = server.url("/").toString();
        server.enqueue(new MockResponse()
                .setResponseCode(200).addHeader("Content-Type", ":application/json")
                .addHeader("Accept", "application/json")
                .setBody("{OK}"));
        //server.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));
        HttpCall httpCall = new HttpCall();
        FcmToken fcmToken = new FcmToken("assddsfds");
        int responseCode = httpCall.setFcmToken(fcmToken, "Bearer mock");
        //assertEquals(20,responseCode);
        RecordedRequest request1 = server.takeRequest();
        assertEquals("/user/deviceToken", request1.getPath());
        assertEquals("PUT", request1.getMethod());


    }

    @Test
    public void setUserLocation() throws Exception {
        MockWebServer server;
        server = new MockWebServer();


        server.start();
        ApiUtils.baseUrl = server.url("/").toString();

        server.enqueue(new MockResponse()
                .setResponseCode(200).addHeader("Content-Type", ":application/json")
                .addHeader("Accept", "application/json")
                .setBody("{OK}"));

        HttpCall httpCall = new HttpCall();
        Location location = new Location(47.477, 77.444);
        int responseCode = httpCall.setUserLocation(location, "Bearer mock");
        // assertEquals(20,responseCode);
        RecordedRequest request1 = server.takeRequest();
        assertEquals("/user/deviceLocation", request1.getPath());
        assertEquals("PUT", request1.getMethod());
        server.shutdown();
    }*/