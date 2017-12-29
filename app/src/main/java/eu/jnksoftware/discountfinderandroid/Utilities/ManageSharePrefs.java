package eu.jnksoftware.discountfinderandroid.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import eu.jnksoftware.discountfinderandroid.models.Location;
import eu.jnksoftware.discountfinderandroid.models.token.User;


public class ManageSharePrefs {

    private static SharedPreferences mSharedPref;


    private ManageSharePrefs() {

    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static User readUser(String defValue) {
        User tempUser;
        if (mSharedPref.contains("userData")) {
            String userToString = mSharedPref.getString("userData", defValue);
            Gson userJson = new Gson();
            tempUser = userJson.fromJson(userToString, User.class);
        } else {
            tempUser = null;
        }

        return tempUser;
    }

    public static void writeUser(User user) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        Gson userJson = new Gson();
        String userString = userJson.toJson(user);
        prefsEditor.putString("userData", userString);
        prefsEditor.commit();
    }

    public static void writeFcmTokenData(String fcmData) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString("fcmUSerData", fcmData);
        prefsEditor.commit();

    }

    public static String readFcmTokenData(String defValue) {
        String tempFcmData;

        if (mSharedPref.contains("fcmUserDate")) {
            tempFcmData = mSharedPref.getString("fcmUSerData", defValue);
        } else {
            tempFcmData = " ";
        }

        return tempFcmData;

    }

    public static void writeLocation(Location location) {
        Gson userJson = new Gson();
        String locationToString = userJson.toJson(location);
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString("userLocation", locationToString);
        prefsEditor.commit();

    }

    public static Location readLocation(String defValue) {
        Location myTempLocation;

        if (mSharedPref.contains("userLocation")) {
            String locationToString = mSharedPref.getString("userLocation", defValue);
            Gson userJson = new Gson();
            myTempLocation = userJson.fromJson(locationToString, Location.class);
        } else {
            myTempLocation = null;
        }

        return myTempLocation;
    }

}
