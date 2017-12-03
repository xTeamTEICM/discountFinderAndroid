package eu.jnksoftware.discountfinderandroid.services;

//import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesPostRequest;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by nikos on 1/12/2017.
 */

public interface IuserService {
    @POST("login")
    Call<UserTokenResponse> getTokenAcess(@Body UserTokenRequest userTokenRequest);

    @PUT("requestDiscount/3")
    Call<DiscountPreferencesResponse> putDiscountPreferences(@Body DiscountPreferencesRequest discountPreferencesRequest);

    @POST("requestDiscount")
    Call<DiscountPreferencesRequest> postDiscountPreferences(@Body DiscountPreferencesPostRequest discountPreferencesPostRequest);

    @GET("requestDiscount/1")
    Call<DiscountPreferencesResponse> getOneDiscountPreference();

    @GET("requestDiscount")
    Call<DiscountPreferencesResponse>getDiscountsPreference();

    @DELETE("requestDiscount/3")
    Call<Void> deleteDiscountPreference();


}