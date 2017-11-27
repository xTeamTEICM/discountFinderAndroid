package eu.jnksoftware.discountfinderandroid.Apis;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.Discount;
import eu.jnksoftware.discountfinderandroid.models.DiscountCategory;

public class CategoriesAPI {

    Context context;
    ArrayList<DiscountCategory> categories;
    String categories_url = "http://83.212.117.108:9001/api/category";

    public CategoriesAPI(Context context){
        this.context = context;
    }

    public ArrayList<DiscountCategory> getCategories(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, categories_url
                , null
                , new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int counter=0;
                        while(counter<response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(counter);
                                DiscountCategory category = new DiscountCategory(jsonObject.getString("id")
                                        ,jsonObject.getString("title"));
                                categories.add(category);
                                counter++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Could not fetch categories,check that you have an internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        Singleton.getmInstance(context).addToRequestQueue(jsonArrayRequest);

        return categories;
    }

}
