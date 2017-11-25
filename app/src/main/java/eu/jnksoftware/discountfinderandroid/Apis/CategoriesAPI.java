package eu.jnksoftware.discountfinderandroid.Apis;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class CategoriesAPI {
    String categories_url = "http://............";

    public void loadCategories(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST
                , categories_url, null /*request body*/, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
