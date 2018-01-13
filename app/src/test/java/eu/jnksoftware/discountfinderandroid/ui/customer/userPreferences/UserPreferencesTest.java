package eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikos on 14/1/2018.
 */
public class UserPreferencesTest {

    @Test
    public void ValidateTest() throws Exception{
        Boolean vali;
        UserPreferences userPreferences=new UserPreferences();
        int min=1;
        vali=userPreferences.validate(min);
        Assert.assertEquals("true",vali.toString());
    }

}