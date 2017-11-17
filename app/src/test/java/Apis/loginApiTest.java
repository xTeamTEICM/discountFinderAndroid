package Apis;

/**
 * Created by nikos on 17/11/2017.
 */

import org.junit.Test;

import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.models.User;

import static org.junit.Assert.*;

public class loginApiTest {
    //String mockLoginRespone="{\"token_type\":\"Bearer\",\"expires_in\":\"7000\",\"access_token\":\"success\",\"refresh_token\":\"refreshsuccess\"}";
    @Test
    public void testAPIAttritubutes() throws IOException{
        User user=new User();
        user.setTokenType("Bearer");
        user.setExpireToken("7000");
        user.setRefreshToken("refreshsucces");
        user.setAccessToken("success");
        assert user.getAccessToken().equals("success");
        assert user.getExpireToken().equals("7000");
        assert user.getRefreshToken().equals("refreshsuccess");
        assert user.getTokenType().equals("Bearer");

    }

}