package eu.jnksoftware.discountfinderandroid.models.token;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTokenRequest {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    public UserTokenRequest() {
    }

    public String getUsername() {
        return username;
    }

    public UserTokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

