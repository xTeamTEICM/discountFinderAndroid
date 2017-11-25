package eu.jnksoftware.discountfinderandroid.Apis;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CategoriesAPI {
    String categories_url = "http://83.212.117.108:9001/api/category";

    public CategoriesAPI() {
    }

    public void loadCategoriesToList(final Context context, final List categories){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                , categories_url, null /*request body*/, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    categories.add(response.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context
                        , "Could not fetch categories,you propably are not connected on the internet"
                        , Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        Singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);

    }
}
