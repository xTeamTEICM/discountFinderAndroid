package eu.jnksoftware.discountfinderandroid.models.discountPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DiscountPreferencesPostResponse {

    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getUserid() {
        return userid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
