package eu.jnksoftware.discountfinderandroid.api;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by iordkost on 24/10/2017.
 */

public interface IAPI {
    boolean load(String json, Location currentLocation);

    ArrayList getList();
}
