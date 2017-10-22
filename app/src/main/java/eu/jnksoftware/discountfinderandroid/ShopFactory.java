package eu.jnksoftware.discountfinderandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dito on 10/21/2017.
 */


public class ShopFactory {
    private List<Shop> shopList;
    private List<Shop> sortedShopList;
    private Position myPosition;

    public ShopFactory() {

        // Call real constructor
        this(new ArrayList<Shop>(), new Position(50.25,45.37));

        // Load Fake Data
        Position position = new Position(40.38,52.36);
        Shop shop = new Shop("Starbucks",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(41.38,40.36);
        shop = new Shop("Coffee Isnald",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(100.38,100.36);
        shop = new Shop("Masoutis",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(200.38,150.36);
        shop = new Shop("LIDL",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(110.38,100.36);
        shop = new Shop("Carrefour",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(140.38,100.36);
        shop = new Shop("Sklavenitis",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(300.38,300.36);
        shop = new Shop("Marinopoulos",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(35.69,40.85);
        shop = new Shop("O kaluteros",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(50.38,90.36);
        shop = new Shop("Family Kitchen",position,calculateDistance(position));
        shopList.add(shop);
        position = new Position(64.38,66.36);
        shop = new Shop("Todaylicious",position,calculateDistance(position));
        shopList.add(shop);

    }

    public ShopFactory(List<Shop> shopList, Position myPosition) {
        this.shopList = shopList;
        this.myPosition = myPosition;
        this.sortedShopList = new ArrayList<>();
    }

    public double calculateDistance(Position position){
        double distance;
        double subtractionX = Math.abs(position.getX() - myPosition.getX());
        double subtractionY = Math.abs(position.getY() - myPosition.getY());
        distance = Math.sqrt((Math.pow(subtractionX,2)) + (Math.pow(subtractionY,2)));
        return distance;
    }

    public void sortShopList() {
        List<Shop> tempShopList = shopList;
        Shop nearestShop;
            while (!(tempShopList.size() == 0))
            {
            nearestShop = tempShopList.get(0);
            for (Shop shops : tempShopList) {
                if (shops.distanceFromUser < nearestShop.distanceFromUser) {
                    nearestShop = shops;
                }
            }
            sortedShopList.add(nearestShop);
            tempShopList.remove(nearestShop);
        }
    }

}
