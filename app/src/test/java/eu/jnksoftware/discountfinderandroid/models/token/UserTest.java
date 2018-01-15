package eu.jnksoftware.discountfinderandroid.models.token;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by makis on 12/1/2018.
 */
public class UserTest {

    User user;
    @Before
    public void setUp() throws Exception {
        user=new User("token_type",7800,"access_token","refresh_token");
    }

    @Test
    public void getTokenType() throws Exception {
        assertEquals("token_type", user.getTokenType());
    }

    @Test
    public void setTokenType() throws Exception {
        user.setTokenType("new_token_type");
        assertEquals("new_token_type", user.getTokenType());
    }

    @Test
    public void getExpiresIn() throws Exception {
        int expiresIn=user.getExpiresIn();
        assertEquals(7800, expiresIn);
    }

    @Test
    public void setExpiresIn() throws Exception {
        user.setExpiresIn(8600);
        int expiresIn=user.getExpiresIn();
        assertEquals(8600, expiresIn);

    }

    @Test
    public void getAccessToken() throws Exception {
        assertEquals("access_token", user.getAccessToken());
    }

    @Test
    public void getRefreshToken() throws Exception {
        assertEquals("refresh_token", user.getRefreshToken());
    }

    @Test
    public void setAccessToken() throws Exception {
        user.setAccessToken("new_access_token");
        assertEquals("new_access_token", user.getAccessToken());
    }

    @Test
    public void setRefreshToken() throws Exception {
        user.setRefreshToken("new_refresh_token");
        assertEquals("new_refresh_token", user.getRefreshToken());

    }

}