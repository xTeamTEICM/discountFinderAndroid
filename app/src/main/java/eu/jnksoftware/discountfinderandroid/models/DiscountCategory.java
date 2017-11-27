package eu.jnksoftware.discountfinderandroid.models;


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
}
