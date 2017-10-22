package eu.jnksoftware.discountfinderandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 21/10/2017
 * License: Apache License 2.0
 */
public class JSONParser {
    private JSONObject content;

    public JSONParser(String originalContent) throws Exception {
        if (originalContent == null || originalContent.isEmpty()) {
            throw new Exception("Empty JSON String");
        } else {
            try {
                content = new JSONObject(originalContent);
                System.out.println();
            } catch (JSONException ex) {
                throw new Exception("Invalid JSON String");
            }
        }
    }

    public boolean has(String key) {
        return content.has(key);
    }

    public String getValue(String key) {
        try {
            return content.get(key).toString();
        } catch (JSONException e) {
            return "NULL";
        }
    }

    public JSONArray getArray(String key) {
        try {
            return content.getJSONArray(key);
        } catch (JSONException e) {
            return null;
        }
    }
}
