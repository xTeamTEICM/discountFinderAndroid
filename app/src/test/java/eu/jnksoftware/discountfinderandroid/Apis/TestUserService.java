package eu.jnksoftware.discountfinderandroid.Apis;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.models.token.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;

public class TestUserService extends TestCase {
   /* IuserService s = ApiUtils.getUserService();
    UserTokenRequest userTokenRequest = new UserTokenRequest();
    @Before
    protected void setUp() throws Exception {

        IuserService s = ApiUtils.getUserService();
        UserTokenRequest userTokenRequest = new UserTokenRequest();
        userTokenRequest.setUsername("user@jnksoftware.eu");
        userTokenRequest.setPassword("myPassword");
    }
*/
    @Test
    public void testTokenType() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        UserTokenRequest userTokenRequest = new UserTokenRequest();
        userTokenRequest.setUsername("user@jnksoftware.eu");
        userTokenRequest.setPassword("myPassword");
        UserTokenResponse userTokenResponse = s.getTokenAcess(userTokenRequest).execute().body();
        assertEquals("Bearer", userTokenResponse.getTokenType().toString());
    }
    public void testAccesType() throws IOException{
        IuserService s = ApiUtils.getMockUserService();
        UserTokenRequest userTokenRequest = new UserTokenRequest();
        userTokenRequest.setUsername("n@gmail.com");
        userTokenRequest.setPassword("123455");

        UserTokenResponse userTokenResponse = s.getTokenAcess(userTokenRequest).execute().body();
        assertEquals("mock_access", userTokenResponse.getAccessToken().toString());
    }
    public void testExpiresIn() throws IOException{
        IuserService s = ApiUtils.getMockUserService();
        UserTokenRequest userTokenRequest = new UserTokenRequest();
        userTokenRequest.setUsername("n@gmail.com");
        userTokenRequest.setPassword("123455");

        UserTokenResponse userTokenResponse = s.getTokenAcess(userTokenRequest).execute().body();
        assertEquals("7199", userTokenResponse.getExpiresIn().toString());
    }
    public void testRefreshToken() throws IOException{
        IuserService s = ApiUtils.getMockUserService();
        UserTokenRequest userTokenRequest = new UserTokenRequest();
        userTokenRequest.setUsername("n@gmail.com");
        userTokenRequest.setPassword("123455");

        UserTokenResponse userTokenResponse = s.getTokenAcess(userTokenRequest).execute().body();
        assertEquals("mock_refresh", userTokenResponse.getRefreshToken().toString());
    }
}