package eu.jnksoftware.discountfinderandroid.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.services.INetwork;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 28/10/2017
 * License: Apache License 2.0
 */
public class CategoryAPI {
    private static final String File = "category/";
    private INetwork network;

    public CategoryAPI(INetwork network) {
        this.network = network;
    }

    public ArrayList<Category> list() {
        ArrayList<Category> result = new ArrayList<>();

        try {
            network.setURL(network.getURL() + File);
            network.call();
            JSONObject response = new JSONObject(network.getResult()).getJSONObject("result");

            if (response.getString("status").equals("OK")) {
                JSONArray categories = response.getJSONArray("categories");

                for (int i = 0; i < categories.length(); i++) {
                    JSONObject category = categories.getJSONObject(i);
                    result.add(new Category(category.getInt("id"), category.getString("title")));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
