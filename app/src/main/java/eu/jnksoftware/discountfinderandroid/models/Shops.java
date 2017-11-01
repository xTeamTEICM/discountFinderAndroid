package eu.jnksoftware.discountfinderandroid.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by iordkost on 25/10/2017.
 */

public class Shops implements Serializable {
    private final ArrayList<Shop> list;

    public Shops() {
        list = new ArrayList<>();
    }

    public Shops(ArrayList<Shop> unsortedList) {
        this.list = new ArrayList<>();
        sort(unsortedList);
    }

    public ArrayList<Shop> getList() {
        return list;
    }

    public Shop getShop(String name) {
        for (Shop shop : list) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
    }

    private void sort(ArrayList<Shop> unsortedList) {
        Shop nearestShop;
        while (!(unsortedList.size() == 0)) {
            nearestShop = unsortedList.get(0);
            for (Shop shops : unsortedList) {
                if (shops.getDistanceFromUser() < nearestShop.getDistanceFromUser()) {
                    nearestShop = shops;
                }
            }
            this.list.add(nearestShop);
            unsortedList.remove(nearestShop);
        }
    }


}
