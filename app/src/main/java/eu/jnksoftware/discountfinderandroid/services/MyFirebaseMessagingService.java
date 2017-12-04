package eu.jnksoftware.discountfinderandroid.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.FullDiscount;

/**
 * Created by makis on 23/11/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title="";
        String message="";
        String click_action="";
        JSONObject data = null;

        if (remoteMessage.getData().size()>0){

            data=new JSONObject(remoteMessage.getData());

        }

        if (remoteMessage.getNotification() !=null){
            title =remoteMessage.getNotification().getTitle();
            message = remoteMessage.getNotification().getBody();
            click_action = remoteMessage.getNotification().getClickAction();
        }


        try {
            sendNotification(title,message,click_action,data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void sendNotification(String title,String messageBody,String click_action,JSONObject jsonObject) throws JSONException {
        Intent intent;
        intent=new Intent(getApplicationContext(),FullDiscount.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("shopName",jsonObject.getString("shopName"));
        intent.putExtra("description",jsonObject.getString("description"));
        intent.putExtra("price",jsonObject.getString("price"));




        PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),0,intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,"1");
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(messageBody);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSound(defaultSoundUri);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify("app",1,notificationBuilder.build());

    }
}

