package eu.jnksoftware.discountfinderandroid.Apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nikos on 1/12/2017.
 */


public class RetrofitClient {
    private static Retrofit retrofit=null;

    public static Retrofit getClient(String url){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit; }
}