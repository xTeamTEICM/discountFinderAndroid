package eu.jnksoftware.discountfinderandroid.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import eu.jnksoftware.discountfinderandroid.models.token.User;


/**
 * Created by makis on 21/12/2017.
 */

public class ManageSharePrefs {

    private static SharedPreferences mSharedPref;
    public static final String NAME = "NAME";
    public static final String AGE = "AGE";
    public static final String IS_SELECT = "IS_SELECT";

    private ManageSharePrefs()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static User readUser(String defValue) {
        User tempUser;
        if (mSharedPref.contains("userData")){
            String userToString = mSharedPref.getString("userData", defValue);
            Gson userJson = new Gson();
            tempUser = userJson.fromJson(userToString, User.class);
        }
        else{
            tempUser=null;
        }

        return tempUser;
    }

    public static void writeUser(User user) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        Gson userJson=new Gson();
        String userString = userJson.toJson(user);
        prefsEditor.putString("userData", userString);
        prefsEditor.commit();
    }

    public static void writeFcmTokenData(String fcmData){
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString("fcmUSerData", fcmData);
        prefsEditor.commit();

    }

    public static String  readFcmTokenData(String defValue){
        String tempFcmData;

        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        if (mSharedPref.contains("fcmUserDate")){
            tempFcmData = mSharedPref.getString("fcmUSerData", defValue);
        }
        else{
            tempFcmData=" ";
        }

        return tempFcmData;

    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);

    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

   /* SharedPreferences userData = getSharedPreferences("myData", MODE_PRIVATE);
        if (userData.contains("userData")) {
        String userToString = userData.getString("userData", "");
        Gson userJson = new Gson();
        User tempUser = userJson.fromJson(userToString, User.class);
        Toast.makeText(Login.this, "wowooww"+tempUser.getTokenType(), Toast.LENGTH_SHORT).show();
    }
        else{
        Toast.makeText(Login.this,"paparia", Toast.LENGTH_SHORT).show();
    }*/
}
