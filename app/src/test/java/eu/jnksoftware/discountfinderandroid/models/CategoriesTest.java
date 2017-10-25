package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iordkost on 25/10/2017.
 */
public class CategoriesTest {

    @Test
    public void exists() throws Exception {
        Categories test = new Categories();
        assertTrue(test.isValid("Υπολογιστες"));
    }

    @Test
    public void notExistA() throws Exception {
        Categories test = new Categories();
        assertFalse(test.isValid("dsadasdasd"));
    }

    @Test
    public void notExistB() throws Exception {
        Categories test = new Categories();
        assertFalse(test.isValid("ypologistes"));
    }

    @Test
    public void notExistC() throws Exception {
        Categories test = new Categories();
        assertFalse(test.isValid("pizza"));
    }

    @Test
    public void getList() throws Exception {
        Categories test = new Categories();
        assertNotNull(test.getList());
    }

}