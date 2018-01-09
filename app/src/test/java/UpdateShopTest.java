import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.services.IuserService;

import static org.junit.Assert.*;

/**
 * Created by dito on 1/1/2018.
 */
public class UpdateShopTest {
    IuserService apiService;

    @Before
    public void setUp() throws Exception {
        apiService = ApiUtils.getUserService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateShopTest() throws Exception{

    }

}