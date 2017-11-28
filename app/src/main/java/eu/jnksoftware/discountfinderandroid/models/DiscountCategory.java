package eu.jnksoftware.discountfinderandroid.models;


import java.util.ArrayList;

public class DiscountCategory {
    private String categoryId;
    private String categoryTitle;

    public DiscountCategory(String id,String title){
        this.setCategoryId(id);
        this.setCategoryTitle(title);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public ArrayList<String> getCategories(ArrayList<DiscountCategory> arrayList){
        ArrayList<String> tempCategoties=new ArrayList<>();
        for(int i=0;i<arrayList.size();i++){
            tempCategoties.add(arrayList.get(i).getCategoryTitle());
        }
        return tempCategoties;
    }
}
