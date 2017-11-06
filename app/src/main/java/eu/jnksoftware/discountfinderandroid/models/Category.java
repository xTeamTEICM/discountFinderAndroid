package eu.jnksoftware.discountfinderandroid.models;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 28/10/2017
 * License: Apache License 2.0
 */
public class Category {
    private int id;
    private String title;

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    // this toString prints on ListView
    public String toString() {
        return getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
