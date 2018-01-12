package eu.jnksoftware.discountfinderandroid.test;

import android.support.test.rule.ActivityTestRule;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.HttpCall;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.general.Main;

import static org.junit.Assert.*;

/**
 * Created by makis on 1/1/2018.
 */

@RunWith(JUnit4.class)
public class HttpCallTest {
    MockWebServer server;
    MockContext mockContext=new MockContext();

    @Before
    public void setUp() throws Exception {

        server = new MockWebServer();
        server.start();
       // ApiUtils.baseUrl = server.url("/").toString();
    }



 /*   @Test
    public void setFcmToken() throws Exception {
        String fileName = "quote_200_ok_response.json";
        HttpCall httpCall=new HttpCall();
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(mockContext,fileName   2)));
        int responce=httpCall.setFcmToken(new FcmToken("fdsfadadfdas"),"Bearer adsafafafafaf");
        assertEquals(200,responce);
    }

    @Test
    public void setUserLocation() throws Exception {
    }

    @Test
    public void refreshToken() throws Exception {
    }
*/
    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

}