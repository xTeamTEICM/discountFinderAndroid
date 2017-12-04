package eu.jnksoftware.discountfinderandroid.Apis;

public class PostShop {
 private String brandName;
 private double lonPos;
 private double latPost;

    public PostShop(String brandName, double lonPos, double latPost) {
        this.brandName = brandName;
        this.lonPos = lonPos;
        this.latPost = latPost;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getLonPos() {
        return lonPos;
    }

    public void setLonPos(double lonPos) {
        this.lonPos = lonPos;
    }

    public double getLatPost() {
        return latPost;
    }

    public void setLatPost(double latPost) {
        this.latPost = latPost;
    }
}
