package eu.jnksoftware.discountfinderandroid.services;

import org.json.JSONArray;

import java.util.List;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
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
import retrofit2.http.Url;


public interface IuserService {
    @POST("login")
    Call<UserTokenResponse> getTokenAcess(@Body UserTokenRequest userTokenRequest);

    @POST("register")
    Call<UserTokenResponse> register(@Body RegisterTokenRequest registerTokenRequest);

    @PUT("requestedDiscount/{id}")
    Call<DiscountPreferencesPostResponse> putDiscountPreferences(@Path("id")int id,@Body DiscountPreferencesRequest discountPreferencesRequest,@Header("Authorization") String auth);

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