package eu.jnksoftware.discountfinderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Shop extends AppCompatActivity {

    String shopName;
    Position location;
    double distanceFromUser;

    public Shop(String shopName, Position location,double distanceFromUser) {
        this.shopName = shopName;
        this.location = location;
        this.distanceFromUser = distanceFromUser;
    }

}
