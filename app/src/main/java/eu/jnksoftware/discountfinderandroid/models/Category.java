package eu.jnksoftware.discountfinderandroid.models;


import java.util.ArrayList;
import java.util.List;

public class Category {
    private String id;
    private String title;

    public Category(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category() {

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCategoriesTitleFromList(List<Category> list){
        List<String> returnList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            returnList.add(list.get(i).getTitle());
        }
        return returnList;
    }

}
