package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Onculy on 6.10.2015.
 */
public class Alarm extends BroadcastReceiver{

    private NotificationManager mNotificationManager;
    private Integer mIncrementalNotificationId = Integer.valueOf(0);
    private String messages[];
    private static int index = 0;
    @Override
    public void onReceive(Context context, Intent ıntent) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        messages = new String[]
                {
                        "Bugün hiç şekerini ölçtün mü?\nBu aralar sık olarak takip etmende yarar var",
                        "Bol bol suyundan içmeyi unutma\nBiliyorsun günde en az 2 lt su tüketmelisin",
                        "Hadi yemeklerinden tuzu çıkar!\nGünde bir çay kaşığı kadar tuz yeter, unutma",
                        "Çavdar ekmeği veya tam buğday ekmeğinin\nçok sağlıklı olduğunu unutma...",
                        "Iyyykkk... \nYağlı yemek yemeyelim sakın...",
                        "Havucu yemeklerimizde dikkatli ve ölçülü tüketmemiz\ngerektiğini biliyor musunuz? -Agah Burak DEMİRKAN",
                };

        Log.e("index", ""+index);
        createNotification4(context, messages[index]);
        if(++index == messages.length)
            index = 0;
    }

    private void createNotification(Context context, String msg) {
        // BEGIN_INCLUDE(notificationCompat)
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("ViaDiabet");

        notificationBuilder.setContentText(msg);
        notificationBuilder.setSmallIcon(R.mipmap.ic_siringa);

        mIncrementalNotificationId = new Integer(mIncrementalNotificationId + 1);
        mNotificationManager.notify(mIncrementalNotificationId, notificationBuilder.build());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        // END_INCLUDE(notificationCompat)

        //Create Intent to launch this Activity again if the notification is clicked.
        Intent i = new Intent(context, ViaDiabet.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        // END_INCLUDE(intent)
        builder.setTicker(context.getResources().getString(R.string.custom_notification));

        // Sets the small icon for the ticker
        builder.setSmallIcon(R.drawable.cast_ic_notification_0);
        // END_INCLUDE(ticker)

        builder.setAutoCancel(true);

        // Build the notification
        Notification notification = builder.build();
        // END_INCLUDE(buildNotification)
        Log.e("SDK VERSION", ""+Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView =
                    new RemoteViews(context.getPackageName(), R.layout.notification_expanded);
            //expandedView.setTextViewText(R.id.notifTV, msg);
            notification.bigContentView = expandedView;

        }
        // END_INCLUDE(customLayout)
        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify(0, notification);
        // END_INCLUDE(notify)
    }

    private void createNotification2(Context context, String msg) {

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_expanded);
        contentView.setTextViewText(R.id.notifText, msg);
        //contentView.setTextViewText(R.id.notifTitle, "ViaDiabet");
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("ViaDiabet").setContent(contentView);

        Intent i = new Intent(context, ViaDiabet.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(intent);

        notificationBuilder.setSmallIcon(R.mipmap.sanofi);
        Notification notification = notificationBuilder.build();

        mIncrementalNotificationId = mIncrementalNotificationId + 1;
        mNotificationManager.notify(mIncrementalNotificationId, notification);

    }

    private void createNotification3(Context context, String msg) {

        //contentView.setTextViewText(R.id.notifTitle, "ViaDiabet");

        Intent i = new Intent(context, ViaDiabet.class);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("ViaDiabet");
        notificationBuilder.addAction(R.mipmap.ic_action_direction, "Yes", intent);
        notificationBuilder.addAction(R.mipmap.ic_action_refresh, "No", intent);

        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        notificationBuilder.setContentIntent(intent);

        notificationBuilder.setSmallIcon(R.mipmap.sanofi);
        Notification notification = notificationBuilder.build();

        mIncrementalNotificationId = mIncrementalNotificationId + 1;
        mNotificationManager.notify(mIncrementalNotificationId, notification);

    }

    private void createNotification4(Context context, String msg) {

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_et);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("ViaDiabet");

        Intent i = new Intent(context, ViaDiabet.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(intent);

        notificationBuilder.setSmallIcon(R.mipmap.sanofi);
        Notification notification = notificationBuilder.build();

        mIncrementalNotificationId = mIncrementalNotificationId + 1;
        mNotificationManager.notify(mIncrementalNotificationId, notification);

    }

}
