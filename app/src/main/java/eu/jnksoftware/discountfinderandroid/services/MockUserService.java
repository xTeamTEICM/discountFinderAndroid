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
        return delegate.returningResponse(null).deleteDiscountPreference(id,auth);
    }

    @Override
    public Call<List<Category>> fetchCategories() {
         List<Category> testCategory=new ArrayList<>();
        Collections.addAll(testCategory,new Category("1","title1"));
        return delegate.returningResponse(testCategory).fetchCategories();
    }

    @Override
    public Call<List<Discount>> getDiscounts(int distance, String auth) {
        List<Discount> testDiscounts=new ArrayList<>();
        Collections.addAll(testDiscounts,new Discount(1,"food","food","Pizza",10,"image",200,23.88,43.27)
                ,new Discount(2,"pc","AlienWare","PcStation",950,"image2",590,23.27,22.88)
                ,new Discount(3,"pc","AlienWare","PcStation",550,"image3",122,43.27,27.88)
                ,new Discount(4,"workstation","asus","PcStation",350,"image4",190,43.27,62.88)
                ,new Discount(5,"pc","Hp","PcStation",450,"image5",320,53.27,10.88));

        return delegate.returningResponse(testDiscounts).getDiscounts(1000,auth);
    }

    @Override
    public Call<List<TopDiscount>> getTopDiscounts(int distance, String auth) {
        List<TopDiscount> topDiscounts=new ArrayList<>();
        Collections.addAll(topDiscounts,new TopDiscount("short","image1"));
        return delegate.returningResponse(topDiscounts).getTopDiscounts(distance,auth);
    }

    @Override
    public Call<Void> deleteShop(int id, String auth) {
        return delegate.returningResponse(null).deleteShop(id,auth);
    }

    @Override
    public Call<List<Shop>> getUserShops(String auth) {
        Location location=new Location();
        location.setLatPos(12);
        location.setLogPos(10);
        List<Shop> testShops=new ArrayList<>();
        Collections.addAll(testShops,new Shop(1,1,"brand1",location));
        return delegate.returningResponse(testShops).getUserShops(auth);
    }

    @Override
    public Call<Void> addShop(PostShop postShop, String auth) {
        return delegate.returningResponse(null).addShop(postShop,auth);
    }

    @Override
    public Call<Void> updateShop(int id,UpdatePostShop updatePostShop, String auth) {
        return delegate.returningResponse(null).updateShop(id,updatePostShop,auth);
    }

    @Override
    public Call<DiscountGet> addDiscount(DiscountPost discountPost, String auth) {
        DiscountGet discountGet=new DiscountGet();
        discountGet.setCategory(1);
        discountGet.setCurrentPrice(20);
        discountGet.setDescription("desc");
        discountGet.setId(1);
        discountGet.setImage("image");
        discountGet.setOriginalPrice(40);
        discountGet.setShopId(1);
        return delegate.returningResponse(discountGet).addDiscount(discountPost,auth);
    }

    @Override
    public Call<List<SellerDiscount>> getSellerDiscounts(int shopId, String auth) {
        List<SellerDiscount> sellerTestDiscount=new ArrayList<>();
        Collections.addAll(sellerTestDiscount,new SellerDiscount(1,1,30,40,"desc","image"));
        return delegate.returningResponse(sellerTestDiscount).getSellerDiscounts(shopId,auth);
    }

    @Override
    public Call<Void> deleteSellerDiscount(int id, String auth) {
        return delegate.returningResponse(null).deleteSellerDiscount(id,auth);
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
