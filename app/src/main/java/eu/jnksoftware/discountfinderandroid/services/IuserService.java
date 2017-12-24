package eu.jnksoftware.discountfinderandroid.services;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.PostDiscount;
import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.UpdatePostShop;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountPost;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.models.token.UserTokenRequest;
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
    Call<User> getTokenAcess(@Body UserTokenRequest userTokenRequest);

    @POST("register")
    Call<User> register(@Body RegisterTokenRequest registerTokenRequest);

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

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @POST("/api/user/findDiscounts")
    Call<List<Discount>> getDiscounts(@Body PostDiscount postDiscount, @Header("Authorization") String auth);

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @DELETE("shop/{id}")
    Call<Void> deleteShop(@Path("id") int id, @Header("Authorization") String auth);

    @GET("shop")
    Call<List<Shop>> getShopsList();

    @GET ("shop/")
    Call<List<Shop>> getShopWithId(@Query("id") int id);

    @GET ("user/shop")
    Call<List<Shop>> getUserShops();

    @GET ("user/shop/")
    Call<List<Shop>> getUserShopWithId(@Query("id") int id);

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @GET ("user/shop/")
    Call<List<Shop>> getUserShops(@Header("Authorization") String auth);

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @POST("shop")
    Call<Void> addShop(@Body PostShop postShop, @Header("Authorization") String auth);



    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @PUT("shop")
    Call<Void> updateShop(@Body UpdatePostShop updatePostShop, @Header("Authorization") String auth);


    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @POST("discount")
    Call<DiscountGet> addDiscount(@Body DiscountPost discountPost, @Header("Authorization") String auth);

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @GET("user/shop/{id}/discounts")
    Call<List<SellerDiscount>> getSellerDiscounts(@Path("id") int shopId,@Header("Authorization") String auth);

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @DELETE("discount/{id}")
    Call<Void> deleteSellerDiscount(@Path("id") int id,@Header("Authorization") String auth);

    //set the devicetoken for fcm notifications

    @Headers({("Content-Type:application/json"),("Accept:application/json")})
    @POST("user/deviceToken")
    Call<Void> registerFcmToken(@Body FcmToken deviceToken, @Header("Authorization") String auth);



}