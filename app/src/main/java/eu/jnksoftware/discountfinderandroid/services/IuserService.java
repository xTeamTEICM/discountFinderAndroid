package eu.jnksoftware.discountfinderandroid.services;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.token.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.UserTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IuserService {
    @POST("login")
    Call<UserTokenResponse> getTokenAcess(@Body UserTokenRequest userTokenRequest);

    @POST("register")
    Call<UserTokenResponse> register(@Body RegisterTokenRequest registerTokenRequest);

    @PUT("requestedDiscount/{id}")
    Call<DiscountPreferencesResponse> putDiscountPreferences(@Path("id")int id,@Body DiscountPreferencesRequest discountPreferencesRequest,@Header("Authorization") String auth);

    @POST("requestedDiscount")
    Call<DiscountPreferencesPostResponse> postDiscountPreferences(@Body DiscountPreferencesRequest discountPreferencesPostRequest,@Header("Authorization") String auth);

    @GET("requestedDiscount/")
    Call<DiscountPreferencesResponse> getOneDiscountPreference(@Query("id")int id);

    @GET("requestedDiscount")
    Call<List<DiscountPreferencesResponse>>getDiscountsPreference(@Header("Authorization")String auth);

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @DELETE("requestedDiscount/{id}")
    Call<Void> deleteDiscountPreference(@Path("id") int id, @Header("Authorization")String auth);

    @GET("category")
    Call<List<Category>> fetchCategories();



}