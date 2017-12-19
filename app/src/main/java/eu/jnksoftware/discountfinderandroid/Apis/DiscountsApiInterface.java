package eu.jnksoftware.discountfinderandroid.Apis;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface DiscountsApiInterface {

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @POST("/api/user/findDiscounts")
    Call<List<Discount>> getDiscounts(@Body PostDiscount postDiscount, @Header("Authorization") String auth);


}
