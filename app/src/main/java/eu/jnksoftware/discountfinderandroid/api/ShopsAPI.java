package eu.jnksoftware.discountfinderandroid.api;

import android.location.Location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import eu.jnksoftware.discountfinderandroid.models.Shop;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 22/10/2017
 * License: Apache License 2.0
 */
public class ShopsAPI implements IAPI {

    private ArrayList<Shop> list;

    @Override
    public boolean load(String json, Location currentLocation) {

        boolean status = false;

        try {
            list = new ArrayList<>();
            JSONObject fullJSON = new JSONObject(json);
            JSONArray shopArray = fullJSON.getJSONArray("shops");

            JSONObject shopRecord;
            Location shopLocation;

            for (int i = 0; i < shopArray.length(); i++) {
                shopRecord = shopArray.getJSONObject(i);
                shopLocation = new Location("");
                shopLocation.setLatitude(shopRecord.getDouble("logPos"));
                shopLocation.setLongitude(shopRecord.getDouble("latPos"));

                list.add(
                        new Shop(
                                shopRecord.getString("brandName"),
                                new eu.jnksoftware.discountfinderandroid.models.Location(shopLocation),
                                new eu.jnksoftware.discountfinderandroid.models.Location(currentLocation)
                        )
                );

                status = true;
            }
        } catch (Exception ex) {
            status = false;
        }

        return status;
    }

    @Override
    public ArrayList getList() {
        return list;
    }
}
