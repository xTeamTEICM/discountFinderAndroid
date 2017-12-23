package eu.jnksoftware.discountfinderandroid.services;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import eu.jnksoftware.discountfinderandroid.Apis.HttpCall;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.token.FcmToken;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.ui.general.Login;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";
    private FcmToken fcmToken;
    private String auth;
    private HttpCall httpCall;
    private boolean authenticate;
    private int statusCode;
    private User currentUser;

    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);
        fcmToken = new FcmToken(recent_token);

        do {
            currentUser = ManageSharePrefs.readUser(null);
            if (currentUser != null) {
                auth=currentUser.getTokenType()+" "+currentUser.getAccessToken();
                httpCall = new HttpCall();
                statusCode = httpCall.setFcmToken(fcmToken, auth);
                if (statusCode == 200) {
                    ManageSharePrefs.writeFcmTokenData(currentUser.getAccessToken());
                }
                authenticate=true;
            } else {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (authenticate == false);
    }


}