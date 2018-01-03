package eu.jnksoftware.discountfinderandroid.models.discountPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DiscountPreferencesResponse {
    @SerializedName("id")
@Expose
private Integer id;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("tags")
    @Expose
    private String tags;

    @SerializedName("categoryTitle")
    @Expose
    private String categoryTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }



    public String getCategoryTitle() {
        return categoryTitle;
    }

}
