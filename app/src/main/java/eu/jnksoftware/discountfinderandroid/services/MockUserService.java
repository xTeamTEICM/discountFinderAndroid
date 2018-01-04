package eu.jnksoftware.discountfinderandroid.services;

import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.UpdatePostShop;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountPost;
import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

/**
 * Created by nikos on 4/1/2018.
 */

public class MockUserService implements IuserService {
    private final BehaviorDelegate<IuserService> delegate;

    public MockUserService(BehaviorDelegate<IuserService> service){
        this.delegate=service;
    }

    @Override
    public Call<User> login(String username, String password) {
        User user=new User();
        user.setAccessToken("access");
        user.setExpiresIn(5100);
        user.setRefreshToken("5100");
        user.setTokenType("Bearer");
        return delegate.returningResponse(user).login(username,password);
    }

    @Override
    public Call<User> register(RegisterTokenRequest registerTokenRequest) {
        return null;
    }

    @Override
    public Call<DiscountPreferencesResponse> putDiscountPreferences(int id, DiscountPreferencesRequest discountPreferencesRequest, String auth) {
        return null;
    }

    @Override
    public Call<DiscountPreferencesPostResponse> postDiscountPreferences(DiscountPreferencesRequest discountPreferencesPostRequest, String auth) {
        return null;
    }

    @Override
    public Call<DiscountPreferencesResponse> getOneDiscountPreference(int id) {
        return null;
    }

    @Override
    public Call<List<DiscountPreferencesResponse>> getDiscountsPreference(String auth) {
        return null;
    }

    @Override
    public Call<Void> deleteDiscountPreference(int id, String auth) {
        return null;
    }

    @Override
    public Call<List<Category>> fetchCategories() {
        return null;
    }

    @Override
    public Call<List<Discount>> getDiscounts(int distance, String auth) {
        return null;
    }

    @Override
    public Call<List<TopDiscount>> getTopDiscounts(int distance, String auth) {
        return null;
    }

    @Override
    public Call<Void> deleteShop(int id, String auth) {
        return null;
    }

    @Override
    public Call<List<Shop>> getShopsList() {
        return null;
    }

    @Override
    public Call<List<Shop>> getShopWithId(int id) {
        return null;
    }

    @Override
    public Call<List<Shop>> getUserShops() {
        return null;
    }

    @Override
    public Call<List<Shop>> getUserShopWithId(int id) {
        return null;
    }

    @Override
    public Call<List<Shop>> getUserShops(String auth) {
        return null;
    }

    @Override
    public Call<Void> addShop(PostShop postShop, String auth) {
        return null;
    }

    @Override
    public Call<Void> updateShop(UpdatePostShop updatePostShop, String auth) {
        return null;
    }

    @Override
    public Call<DiscountGet> addDiscount(DiscountPost discountPost, String auth) {
        return null;
    }

    @Override
    public Call<List<SellerDiscount>> getSellerDiscounts(int shopId, String auth) {
        return null;
    }

    @Override
    public Call<Void> deleteSellerDiscount(int id, String auth) {
        return null;
    }

    @Override
    public Call<Void> registerFcmToken(FcmToken deviceToken, String auth) {
        return null;
    }

    @Override
    public Call<Void> setUserLocation(Location location, String auth) {
        return null;
    }

    @Override
    public Call<User> refreshAccessToken(String refresh_token) {
        return null;
    }
}
