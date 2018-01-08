package eu.jnksoftware.discountfinderandroid;

/**
 * Created by nikos on 10/12/2017.
 */

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;


import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;

public class TestRegisterService extends TestCase {
    @Test
    public void testTokenType() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String email="nikos@gmail.com";
        String fname="nikos";
        String lname="delis";
        String pass="123456";


        User user = s.register(fname,lname,email,pass).execute().body();
        assertEquals("Bearer", user.getTokenType());

    }
    @Test
    public void testTokenAccess() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String email="nikos@gmail.com";
        String fname="nikos";
        String lname="delis";
        String pass="123456";


        User user = s.register(fname,lname,email,pass).execute().body();
        assertEquals("mock_access", user.getAccessToken().toString());

    }
    @Test
    public void testTokenRefresh() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String email="nikos@gmail.com";
        String fname="nikos";
        String lname="delis";
        String pass="123456";


        User user = s.register(fname,lname,email,pass).execute().body();
        assertEquals("mock_refresh", user.getRefreshToken().toString());

    }
    @Test
    public void testExpiresIn() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String email="nikos@gmail.com";
        String fname="nikos";
        String lname="delis";
        String pass="123456";


        User user = s.register(fname,lname,email,pass).execute().body();
        assertEquals("7199", user.getExpiresIn().toString());

    }


}
