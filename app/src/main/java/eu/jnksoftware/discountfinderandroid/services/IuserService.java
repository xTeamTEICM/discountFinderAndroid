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
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface IuserService {
    @POST("login")
    Call<UserTokenResponse> getTokenAcess(@Body UserTokenRequest userTokenRequest);

    @POST("register")
    Call<UserTokenResponse> register(@Body RegisterTokenRequest registerTokenRequest);

    @PUT("requestedDiscount/3")
    Call<DiscountPreferencesResponse> putDiscountPreferences(@Body DiscountPreferencesRequest discountPreferencesRequest);

    @POST("requestedDiscount")
    Call<DiscountPreferencesPostResponse> postDiscountPreferences(@Body DiscountPreferencesRequest discountPreferencesPostRequest,@Header("Authorization") String auth);

    @GET("requestedDiscount/")
    Call<DiscountPreferencesResponse> getOneDiscountPreference(@Query("id")int id);

    @GET("requestedDiscount")
    Call<List<DiscountPreferencesResponse>>getDiscountsPreference(@Header("Authorization")String auth);

    @DELETE("requestedDiscount/3")
    Call<Void> deleteDiscountPreference();

    @GET("category")
    Call<List<Category>> fetchCategories();



}