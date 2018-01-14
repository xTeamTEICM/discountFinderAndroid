package eu.jnksoftware.discountfinderandroid.models;


public class Shop {

    private final int id;
    private final int ownerId;
    private String brandName;
    private final Location location;


    public Shop(int id,int ownerId,String brandName, Location location) {
        this.id = id;
        this.ownerId = ownerId;
        this.brandName = brandName;
        this.location = location;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setName(String brandName) {
        this.brandName = brandName;
    }

    public Location getLocation() {
        return location;
    }
}
