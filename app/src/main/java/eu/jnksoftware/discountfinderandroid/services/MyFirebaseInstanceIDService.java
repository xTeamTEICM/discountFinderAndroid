package eu.jnksoftware.discountfinderandroid.services;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;

import eu.jnksoftware.discountfinderandroid.Apis.HttpCall;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.User;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN="REG_TOKEN";
    private FcmToken fcmToken;
    private String auth;
    private HttpCall httpCall;
    private boolean authenticate;
    private int statusCode;
    @Override
    public void onTokenRefresh() {
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,recent_token);
        fcmToken=new FcmToken(recent_token);

        SharedPreferences userData = getSharedPreferences("myData", MODE_PRIVATE);

        do {
            if (userData.contains("userData")) {
                String userToString = userData.getString("userData", "");
                Gson userJson = new Gson();
                User tempUser = userJson.fromJson(userToString, User.class);
                auth = tempUser.getTokenType()+" "+tempUser.getAccessToken();
                httpCall=new HttpCall();
                statusCode=httpCall.setFcmToken(fcmToken, auth);
                authenticate =true;
            } else {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }while (authenticate ==false);
    }

}