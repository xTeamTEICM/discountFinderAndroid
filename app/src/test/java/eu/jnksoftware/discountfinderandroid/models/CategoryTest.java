package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 28/10/2017
 * License: Apache License 2.0
 */
public class CategoryTest {

    @Test
    public void testGetId() throws Exception {
        Category category = new Category(1, "Test");
        assertEquals(1, category.getId());
    }

    @Test
    public void testGetTitle() throws Exception {
        Category category = new Category(1, "Test");
        assertEquals("Test", category.getTitle());
    }


    @Test
    public void testSetTitle() throws Exception {
        Category category = new Category(1, "Test");
        category.setTitle("Test2");
        assertEquals("Test2", category.getTitle());
    }

    @Test
    public void testToString() throws Exception {
        Category category = new Category(1, "Test");
        assertEquals(category.toString(), category.getTitle());
    }
}