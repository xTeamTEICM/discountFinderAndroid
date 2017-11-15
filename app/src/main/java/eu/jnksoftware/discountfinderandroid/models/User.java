package eu.jnksoftware.discountfinderandroid.models;

/**
 * Created by makis on 26/10/2017.
 */

public class User{

    private String token;
    private String refreshToken;
    private String expireToken;

    public User(String token, String refreshToken, String expireToken) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expireToken = expireToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpireToken() {
        return expireToken;
    }

    public void setExpireToken(String expireToken) {
        this.expireToken = expireToken;
    }

}
