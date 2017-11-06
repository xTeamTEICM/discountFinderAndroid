package eu.jnksoftware.discountfinderandroid.api;

import org.junit.Test;

import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.services.MockCategoryNetwork;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 28/10/2017
 * License: Apache License 2.0
 */
public class CategoryAPITest {

    @Test
    public void listMockNetwork() throws Exception {
        CategoryAPI categoryAPI = new CategoryAPI(new MockCategoryNetwork());
        ArrayList<Category> list = categoryAPI.list();
        assertEquals("Laptops", list.get(0).getTitle());
    }

    @Test
    public void listNullNetwork() throws Exception {
        CategoryAPI categoryAPI = new CategoryAPI(null);
        assertTrue(categoryAPI.list().isEmpty());
    }
}