package eu.jnksoftware.discountfinderandroid.Apis;

import org.json.JSONArray;

import java.util.List;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dito on 12/1/2017.
 */

public interface ShopsApiInterface {

    @GET("shop")
    Call<List<Shop>> getShopsList();

    @GET ("shop/")
    Call<List<Shop>> getShopWithId(@Query("id") int id);

    @GET ("user/shop")
    Call<List<Shop>> getUserShops();

    @GET ("user/shop/")
    Call<List<Shop>> getUserShopWithId(@Query("id") int id);

    @POST ("user/shop")
    Call<List<Shop>> createShop(@Body PostShop shop); //, Callback<PostShop> callback);
}
