package eu.jnksoftware.discountfinderandroid.services;

import eu.jnksoftware.discountfinderandroid.models.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nikos on 1/12/2017.
 */

public interface IuserService {
    @POST("login")
    Call<UserTokenResponse> getTokenAcess(@Body UserTokenRequest userTokenRequest);
}
