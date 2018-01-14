package eu.jnksoftware.discountfinderandroid.models.token;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by makis on 11/1/2018.
 */
public class FcmTokenTest {
    @Test
    public void getDeviceToken() throws Exception {
        FcmToken fcmToken=new FcmToken("dsfgsfsadfpodsagpofpofgsfkgaksfadsfsaaaa");
        assertEquals("dsfgsfsadfpodsagpofpofgsfkgaksfadsfsaaaa",fcmToken.getDeviceToken());
    }

}