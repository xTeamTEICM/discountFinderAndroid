package eu.jnksoftware.discountfinderandroid.Apis;

import java.util.List;
import eu.jnksoftware.discountfinderandroid.models.Discount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *
 * Created by poz on 6/12/2017.
 *
 */

public interface DiscountsApiInterface {

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @POST("/api/user/findDiscounts")
    Call<List<Discount>> getDiscounts(@Body PostDiscount postDiscount, @Header("Authorization") String auth);


}
