package com.example.demonew;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Objects;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Map<String,String> data;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.e("msgdata", "message: "+message );
      //  getfirebasemsg(Objects.requireNonNull(message.getNotification()).getTitle(),message.getNotification().getBody());
        data =message.getData();

        Log.e("msgdata", "onMessageReceived: "+data );
    }

    public void getfirebasemsg(String title, String msg)
    {
        Intent notificationIntent = new Intent(getApplicationContext() , MainScreen. class ) ;
        notificationIntent.addCategory(Intent. CATEGORY_LAUNCHER ) ;
        notificationIntent.setAction(Intent. ACTION_MAIN ) ;
        notificationIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP | Intent. FLAG_ACTIVITY_SINGLE_TOP ) ;
        PendingIntent resultIntent = PendingIntent. getActivity (getApplicationContext() , 0 , notificationIntent , 0 ) ;
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"firebasechannel")
                .setSmallIcon(R.drawable.img)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setContentIntent(resultIntent);

        NotificationManagerCompat compat=NotificationManagerCompat.from(this);
        compat.notify(1,builder.build());
    }


}
