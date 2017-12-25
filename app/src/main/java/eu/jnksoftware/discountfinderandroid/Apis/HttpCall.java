package eu.jnksoftware.discountfinderandroid.Apis;

import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
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

    public String setUserLocation(String rawData , String auth){
        final String[] responseString = {""};
        Call<Void> call =iuserService.setUserLocation(rawData, auth);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()){
                responseString[0] ="200";
                }

            }


            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
            }

        });
        return responseString[0];
    }

}
