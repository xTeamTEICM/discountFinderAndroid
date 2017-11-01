package eu.jnksoftware.discountfinderandroid.models;

import java.util.ArrayList;

/**
 * Created by iordkost on 25/10/2017.
 */

public class Categories {
    private ArrayList<String> list;

    public Categories() {

        list = new ArrayList<>();

        list.add("Υπολογιστες");
        list.add("Πιτσες");
        list.add("Μπλουζες");
        list.add("Ομπρελες");
    }

    public ArrayList<String> getList() {
        return list;
    }

    public boolean isValid(String string) {
        return list.contains(string);
    }
}
