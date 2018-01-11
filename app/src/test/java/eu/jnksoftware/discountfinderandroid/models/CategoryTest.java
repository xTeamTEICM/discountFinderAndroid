package eu.jnksoftware.discountfinderandroid.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by makis on 30/12/2017.
 */
public class CategoryTest {

    @Test
    public void getId() throws Exception {
        Category category=new Category("1","PC");
        assertEquals("1",category.getId());
    }

    @Test
    public void getTitle() throws Exception {
        Category category=new Category("1","PC");
        assertEquals("PC",category.getTitle());
    }

    @Test
    public void getCategoriesTitleFromList() throws Exception {
    }

}