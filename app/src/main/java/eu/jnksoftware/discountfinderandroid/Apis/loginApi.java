package eu.jnksoftware.discountfinderandroid.Apis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.models.User;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;
import eu.jnksoftware.discountfinderandroid.ui.general.Login;

/**
 * Created by nikos on 17/11/2017.
 */

public class loginApi extends AppCompatActivity {

    private static final String LOGIN_URL = "http://83.212.117.108:9001/api/login";
    private static final int timeOutInMs = 10000;
    private static final int numberOfTries = 1;

    public loginApi() {
    }

    public void doLogin(final Context context, JSONObject jsonObject) {
        User user=new User();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                User user=new User();
                try {
                    user.setAccessToken(response.getString("access_token"));
                    user.setExpireToken(response.getString("expires_in"));
                    user.setRefreshToken(response.getString("refresh_token"));
                    user.setTokenType(response.getString("token_type"));

                    Intent intent=new Intent(context,MenuCustomer.class);
                    Gson myGson=new Gson();
                    String myJson = myGson.toJson(user);
                    intent.putExtra("myjson", myJson);
                    context.startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("", "");
                Toast.makeText(context, "Everything Works Fine", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "");
                Toast.makeText(context, "You put wrong password or Email", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Content-Type", "application/json");
                return map;
            }

        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(timeOutInMs, numberOfTries, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        singleton.getmInstance(context).addToRequestQueue(jsonObjectRequest);


    }
}
