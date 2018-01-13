package eu.jnksoftware.discountfinderandroid.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.PostShop;
import eu.jnksoftware.discountfinderandroid.Apis.UpdatePostShop;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.SellerDiscount;
import eu.jnksoftware.discountfinderandroid.models.Shop;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.discountPreferences.DiscountPreferencesResponse;
import eu.jnksoftware.discountfinderandroid.models.discounts.Discount;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountPost;
import eu.jnksoftware.discountfinderandroid.models.discounts.TopDiscount;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

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
    public Call<User> register(String firstName, String lastName, String eMail, String password) {
        User user=new User();
        user.setTokenType("Bearer");
        user.setRefreshToken("refresh");
        user.setAccessToken("access");
        user.setExpiresIn(5000);
        return delegate.returningResponse(user).register(firstName,lastName,eMail,password);
    }

    @Override
    public Call<DiscountPreferencesResponse> putDiscountPreferences(int id, String category, String price, String tags, String auth) {
        DiscountPreferencesResponse discountPreferencesResponse=new DiscountPreferencesResponse(1,1,1,40,"tag","shoe");
        discountPreferencesResponse.setCategory(1);
        discountPreferencesResponse.setId(1);
        discountPreferencesResponse.setPrice(40);
        discountPreferencesResponse.setTags("tag");
        return delegate.returningResponse(discountPreferencesResponse).putDiscountPreferences(id,category,price,tags,auth);
    }

    @Override
    public Call<DiscountPreferencesPostResponse> postDiscountPreferences(String category, String price, String tags, String auth) {

        DiscountPreferencesPostResponse discountPreferencesPostResponse=new DiscountPreferencesPostResponse();
        discountPreferencesPostResponse.setCategory("shoes");
        discountPreferencesPostResponse.setPrice("40");
        discountPreferencesPostResponse.setId(1);
        discountPreferencesPostResponse.setTags("tag");
        return delegate.returningResponse(discountPreferencesPostResponse).postDiscountPreferences(category,price,tags,auth);
    }





    @Override
    public Call<List<DiscountPreferencesResponse>> getDiscountsPreference(String auth) {
        List<DiscountPreferencesResponse> preferencesResponses=new ArrayList<>();
        Collections.addAll(preferencesResponses,new DiscountPreferencesResponse(1,1,1,40,"tag1","shoe")
                ,new DiscountPreferencesResponse(2,2,2,30,"tag2","coffee")
                ,new DiscountPreferencesResponse(3,3,3,20,"tag3","pc"));

       return delegate.returningResponse(preferencesResponses).getDiscountsPreference(auth);

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
        List<Discount> testDiscounts=new ArrayList<>();
        Collections.addAll(testDiscounts,new Discount(1,"food","food","Pizza",10,"image",200,23.88,43.27)
                ,new Discount(2,"pc","AlienWare","PcStation",950,"image2",590,23.27,22.88)
                ,new Discount(3,"pc","Hp","PcStation",450,"image3",320,53.27,10.88));

        return delegate.returningResponse(testDiscounts).getDiscounts(1000,auth);
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

        return delegate.returningResponse(null).registerFcmToken(deviceToken,auth);
    }

    @Override
    public Call<Void> setUserLocation(Location location, String auth) {
        return delegate.returningResponse(null).setUserLocation(location,auth);
    }

    @Override
    public Call<User> refreshAccessToken(String refresh_token) {
        User user=new User();
        user.setTokenType("Bearer");
        user.setRefreshToken("refresh");
        user.setAccessToken("access");
        user.setExpiresIn(5000);
        return delegate.returningResponse(user).refreshAccessToken("refresh_token");

    }
}
