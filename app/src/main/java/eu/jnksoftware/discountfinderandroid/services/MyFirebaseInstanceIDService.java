package eu.jnksoftware.discountfinderandroid.services;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private IuserService iuserService;
    private static final String REG_TOKEN="REG_TOKEN";
    private String auth;
    private boolean authedicate=false;

    @Override
    public void onTokenRefresh() {
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,recent_token);

        SharedPreferences userData = getSharedPreferences("myData", MODE_PRIVATE);

        do {
            if (userData.contains("userData")) {
                String userToString = userData.getString("userData", "");
                Gson userJson = new Gson();
                User tempUser = userJson.fromJson(userToString, User.class);
                auth = tempUser.getTokenType() + " " + tempUser.getAccessToken();
                setFcmToken(recent_token, auth);
                //Toast.makeText(getApplicationContext(), "wowooww", Toast.LENGTH_SHORT).show();
                authedicate=true;
            } else {
                //Toast.makeText(getApplicationContext(), "thread will sleep", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }while (authedicate==false);

    }
    public void setFcmToken(String token, String auth){
        iuserService= ApiUtils.getUserService();
        Call<Void> call =iuserService.registerFcmToken(token, auth);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int statusCode=response.code();
                //Toast.makeText(MenuCustomer.this,"Stetus code: "+Integer.toString(statusCode),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //Toast.makeText(Login.this,"Wrong!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}