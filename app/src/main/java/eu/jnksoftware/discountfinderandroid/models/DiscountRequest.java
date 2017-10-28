package eu.jnksoftware.discountfinderandroid.models;


import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

/**
 *
 * Created by poz on 27/10/2017.
 *
 */

public class DiscountRequest {
    private int id;
    private String category;
    private double price;
    private String tags;
    private Categories categ = new Categories();

    public DiscountRequest(int id, String category, double price,String tags) throws Exception {

            this.id = id;
            this.category = category;
            this.price = price;
            this.tags = tags;

        if(!categ.isValid(category)) throw new Exception("This item does not exist");

    }



    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isAvailable(String category){
        boolean availableString=false;
        ArrayList<String> availableList = categ.getList();
        for(int i=0;i<availableList.size();i++)
        {
            if(availableList.contains(category)){
                availableString = true;
            }
        }
        return availableString;
    }
}
