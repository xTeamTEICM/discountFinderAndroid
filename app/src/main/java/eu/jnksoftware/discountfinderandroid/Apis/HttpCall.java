package eu.jnksoftware.discountfinderandroid.Apis;


import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) throws NullPointerException {
                if (response.isSuccessful()) {
                    statusCode[0] = response.code();
                } else {
                    statusCode[0] = 500;
                }
            }
            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
            }

        });
        return statusCode[0];
    }

    public int setUserLocation(Location location, String auth){
        final int[] statuscode = new int[1];

        Call<Void> call =iuserService.setUserLocation(location, auth);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {


                do {
                    statuscode[0] = response.code();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (statuscode[0] ==0);
            }


            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
            }

        });
        return statuscode[0];
    }

    public void refreshToken(){
        String refresh_token=" ";
        User tempUser= ManageSharePrefs.readUser("");
        if (tempUser!=null){
            refresh_token=tempUser.getRefreshToken();
        }
        Call<User> call =iuserService.refreshAccessToken(refresh_token);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                int statusCode =response.code();
                if (response.isSuccessful()){
                    User user=response.body();
                    ManageSharePrefs.writeUser(user);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
            }

        });

    }

}
