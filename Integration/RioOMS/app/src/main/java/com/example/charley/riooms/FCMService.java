package com.example.charley.riooms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    private final String TAG = "FCM Messaging";

    public FCMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        /*if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();
            String nameField = remoteMessage.getNotification().getBody();

            Log.d(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                    .setContentTitle(title)
                    .setContentText(nameField)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, builder.build());
*/

        /*if(remoteMessage.getData() != null){

            String title = remoteMessage.getData().get("title");
            String nameField = remoteMessage.getData().get("body");

            Notification notification = new NotificationCompat.Builder(this, "channel_id")
                    .setContentTitle(title)
                    .setContentText(nameField)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();

            NotificationManagerCompat managerCompat = new NotificationManagerCompat.(getApplication().getApplicationContext());

        }*/



    }

}
