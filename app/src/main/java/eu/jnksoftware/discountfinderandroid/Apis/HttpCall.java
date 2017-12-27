package eu.jnksoftware.discountfinderandroid.Apis;

import android.util.Log;

import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by makis on 21/12/2017.
 */

public class HttpCall {
    private IuserService iuserService;

    public HttpCall() {
        iuserService = ApiUtils.getUserService();
    }

    public int setFcmToken(FcmToken token, String auth){
        final int[] statusCode = new int[1];
        Call<Void> call =iuserService.registerFcmToken(token, auth);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                statusCode[0] =response.code();
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
            }

        });
        return statusCode[0];
    }

    public String setUserLocation(Location location, String auth){
        final String[] responseString = new String[1];
        Call<Void> call =iuserService.setUserLocation(location, auth);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                responseString[0] ="ok";

            }


            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
            }

        });
        return responseString[0];
    }
    public boolean doRegister( RegisterTokenRequest registerTokenRequest){
        final boolean[] register = new boolean[1];
        Call<User> call=iuserService.register(registerTokenRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int statusCode=response.code();
                if(response.isSuccessful()) {
                    register[0] = true;
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {



            }
        });
  return register[0];
    }

}
