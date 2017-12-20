package eu.jnksoftware.discountfinderandroid.models.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by makis on 21/12/2017.
 */

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

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
