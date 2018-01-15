package eu.jnksoftware.discountfinderandroid.models.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FcmToken {
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;

    public FcmToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

}
