package eu.jnksoftware.discountfinderandroid.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.Position;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.services.Network;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 22/10/2017
 * License: Apache License 2.0
 */
public class ShopsAPI {
    private String jsonString;
    private List<Shop> list;

    public ShopsAPI(String URL) throws Exception {
        Network network = new Network(URL);
        network.call();
        jsonString = network.getResult();

        parseJson();
    }

    private void parseJson() throws Exception {
        list = new ArrayList<>();
        JSONParser jsonParser = new JSONParser(jsonString);

        JSONArray jsonArray = jsonParser.getArray("shops");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(new Shop(jsonObject.getString("brandName"), new Position(jsonObject.getDouble("logPos"), jsonObject.getDouble("latPos")), 0));
        }

    }

    public List<Shop> getList() {
        return list;
    }
}
