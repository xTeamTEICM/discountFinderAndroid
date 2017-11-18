package eu.jnksoftware.discountfinderandroid.Apis;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by nikos on 17/11/2017.
 */

public class Singleton {
    private static Singleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Singleton(Context context){
        mCtx=context;
        requestQueue=getRequestQueue();
    }


    public RequestQueue getRequestQueue(){

        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singleton getmInstance(Context context){
        if(mInstance ==null)
        {
            mInstance=new Singleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);

    }
}
