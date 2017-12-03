package eu.jnksoftware.discountfinderandroid.Apis;

import java.util.List;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopsApiInterface {

    @GET("shop")
    Call<List<Shop>> getShopsList();

    @GET ("shop/")
    Call<List<Shop>> getShopWithId(@Query("id") int id);

    @GET ("user/shop")
    Call<List<Shop>> getUserShops();

    @GET ("user/shop/")
    Call<List<Shop>> getUserShopWithId(@Query("id") int id);

}
