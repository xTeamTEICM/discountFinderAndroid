package eu.jnksoftware.discountfinderandroid;

import org.json.JSONArray;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.api.JSONParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 21/10/2017
 * License: Apache License 2.0
 */
public class JSONParserTest {

    private static final String sampleJSON = "{\"data\":\"helloWorld\",\"array\":[{\"id\":\"1\"}]}";

    @Test
    public void nullContent() throws Exception {
        try {
            new JSONParser(null);
        } catch (Exception ex) {
            assertEquals("Empty JSON String", ex.getMessage());
        }
    }

    @Test
    public void emptyContent() throws Exception {
        try {
            new JSONParser("");
        } catch (Exception ex) {
            assertEquals("Empty JSON String", ex.getMessage());
        }
    }

    @Test
    public void invalidContent() throws Exception {
        try {
            new JSONParser("dsadasdsadasdassdsadsadasdasdsadadsadasdsa");
        } catch (Exception ex) {
            assertEquals("Invalid JSON String", ex.getMessage());
        }
    }

    @Test
    public void validContent() throws Exception {
        try {
            new JSONParser(sampleJSON);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public void hasValueNotExistKey() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            assertTrue(!test.has("userId"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void hasValueExistKey() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            assertTrue(test.has("data"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void getValueNotExistKey() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            assertEquals("NULL", test.getValue("userId"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void getValueExistKey() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            assertEquals("helloWorld", test.getValue("data"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void getArrayNotExistKey() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            assertNull(test.getArray("ADMINS"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void getArrayExistKey() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            assertNotNull(test.getArray("array"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void getArrayContentValid() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            JSONArray jsonArray = test.getArray("array");
            test = new JSONParser(jsonArray.get(0).toString());
            assertEquals("1", test.getValue("id"));
        } catch (Exception ignored) {
        }
    }

    @Test
    public void getArrayContentInValid() throws Exception {
        try {
            JSONParser test = new JSONParser(sampleJSON);
            JSONArray jsonArray = test.getArray("array");
            test = new JSONParser(jsonArray.get(0).toString());
            assertNotEquals("156156156", test.getValue("id"));
        } catch (Exception ignored) {
        }
    }

}