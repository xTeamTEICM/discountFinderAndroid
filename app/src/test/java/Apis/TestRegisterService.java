package Apis;

/**
 * Created by nikos on 10/12/2017.
 */

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.models.token.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;

public class TestRegisterService extends TestCase {
    @Test
    public void testTokenType() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        RegisterTokenRequest registerTokenRequest=new RegisterTokenRequest();
        registerTokenRequest.setLastName("Delis");
        registerTokenRequest.setFirstName("Nikos");
        registerTokenRequest.setEMail("nikos9@gmail.com");
        registerTokenRequest.setPassword("123455");

        User user = s.register(registerTokenRequest).execute().body();
        assertEquals("Bearer", user.getTokenType());

    }
    @Test
    public void testTokenAccess() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        RegisterTokenRequest registerTokenRequest=new RegisterTokenRequest();
        registerTokenRequest.setLastName("Delis");
        registerTokenRequest.setFirstName("Nikos");
        registerTokenRequest.setEMail("nikos9@gmail.com");
        registerTokenRequest.setPassword("123455");

        User user = s.register(registerTokenRequest).execute().body();
        assertEquals("mock_access", user.getAccessToken().toString());

    }
    @Test
    public void testTokenRefresh() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        RegisterTokenRequest registerTokenRequest=new RegisterTokenRequest();
        registerTokenRequest.setLastName("Delis");
        registerTokenRequest.setFirstName("Nikos");
        registerTokenRequest.setEMail("nikos9@gmail.com");
        registerTokenRequest.setPassword("123455");

        User user = s.register(registerTokenRequest).execute().body();
        assertEquals("mock_refresh", user.getRefreshToken().toString());

    }
    @Test
    public void testExpiresIn() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        RegisterTokenRequest registerTokenRequest=new RegisterTokenRequest();
        registerTokenRequest.setLastName("Delis");
        registerTokenRequest.setFirstName("Nikos");
        registerTokenRequest.setEMail("nikos9@gmail.com");
        registerTokenRequest.setPassword("123455");

        User user = s.register(registerTokenRequest).execute().body();
        assertEquals("7199", user.getExpiresIn().toString());

    }


}
