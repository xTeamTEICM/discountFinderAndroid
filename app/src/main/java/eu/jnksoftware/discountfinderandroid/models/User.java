package eu.jnksoftware.discountfinderandroid.models;


public class User{

    private String tokenType;
    private String refreshToken;
    private String expireToken;
    private String accessToken;


    public String getAccessToken() {return accessToken;}

    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

    public String getTokenType() {return tokenType;}

    public void setTokenType(String tokenType) {this.tokenType = tokenType;}

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
