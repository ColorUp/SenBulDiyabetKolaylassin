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
import android.widget.Toast;

import java.util.Map;

/**
 * Created by Onculy on 6.10.2015.
 */
public class Alarm extends BroadcastReceiver{

    private NotificationManager mNotificationManager;
    Notification notification = null;
    private Integer mIncrementalNotificationId = Integer.valueOf(0);
    private String messages[];
    private static int index = 5, type = 2;
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        DataTransferObjects.UserDatalog userDatalog = null;
        if(intent.getAction()!=null)
        {
            String act = intent.getAction().toString();
            if(act.equals("m_1"))
            {
                Log.e("M1", "M1");
                userDatalog = new DataTransferObjects.UserDatalog("agah",120f, 0f,0f, 0f,0f, 0f,0, 0f);
                mNotificationManager.cancelAll();
            }else if(act.equals("m_2"))
            {
                Log.e("M2", "M2");
                userDatalog = new DataTransferObjects.UserDatalog("agah",200f, 0f,0f, 0f,0f, 0f,0, 0f);
                mNotificationManager.cancelAll();
            }else if(act.equals("m_3"))
            {
                Log.e("M3", "M3");
                userDatalog = new DataTransferObjects.UserDatalog("agah",300f, 0f,0f, 0f,0f, 0f,0, 0f);
                mNotificationManager.cancelAll();
            }
            Map<String, String> userDatalogObject = userDatalog.getUserDatalogObject();
            boolean result = DatabaseQuery.Insert("USERDATALOG", userDatalogObject);
            if (result)
                Toast.makeText(context, "Başarılı", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "Başarısız", Toast.LENGTH_LONG).show();

            return;
        }
        messages = new String[]
                {
                        "Bugün hiç şekerini ölçtün mü?\nBu aralar sık olarak takip etmende yarar var",
                        "Bol bol suyundan içmeyi unutma\nBiliyorsun günde en az 2 lt su tüketmelisin",
                        "Hadi yemeklerinden tuzu çıkar!\nGünde bir çay kaşığı kadar tuz yeter, unutma",
                        "Çavdar ekmeği veya tam buğday ekmeğinin\nçok sağlıklı olduğunu unutma...",
                        "Iyyykkk... \nYağlı yemek yemeyelim sakın...",
                        "Havucu yemeklerimizde ölçülü tüketmemiz\ngerektiğini biliyor musunuz?"
                };

        Log.e("type", "" + type);
        if(type == 2)
        {
            createNotification2(context, messages[index]);
        }else if(type == 3)
        {
            createNotification3(context, messages[index]);
            if(++index == messages.length)
                index = 0;
        }else if(type == 4)
        {
            createNotification4(context, messages[index]);
        }
        if(++type == 5)
            type = 2;
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

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_et);

        Intent i = new Intent(context, ViaDiabet.class);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("Son şeker değerin?").setContent(contentView);

        Intent m_1 = new Intent();
        m_1.setAction("m_1");
        PendingIntent intent1 = PendingIntent.getBroadcast(context, 9999, m_1, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.mipmap.d80100, "", intent1);

        Intent m_2 = new Intent();
        m_2.setAction("m_2");
        PendingIntent intent2 = PendingIntent.getBroadcast(context, 9999, m_2, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.mipmap.d120200, "", intent2);

        Intent m_3 = new Intent();
        m_3.setAction("m_3");
        PendingIntent intent3 = PendingIntent.getBroadcast(context, 9999, m_3, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.mipmap.d200plus, "", intent3);

        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //notificationBuilder.setContentIntent(intent);

        notificationBuilder.setSmallIcon(R.mipmap.tick);
        notification = notificationBuilder.build();

        mIncrementalNotificationId = mIncrementalNotificationId + 1;
        mNotificationManager.notify(mIncrementalNotificationId, notification);

    }

    private void createNotification4(Context context, String msg) {
        Log.e("lelele", "lololo");

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_et);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("Bugün yemekte yoğurt yedin mi?");

        Intent i = new Intent(context, ViaDiabet.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.mipmap.tick, "", intent);
        notificationBuilder.addAction(R.mipmap.nocross, "", intent);
        notificationBuilder.setContentIntent(intent);

        notificationBuilder.setSmallIcon(R.mipmap.sanofi);
        Notification notification = notificationBuilder.build();

        mIncrementalNotificationId = mIncrementalNotificationId + 1;
        mNotificationManager.notify(mIncrementalNotificationId, notification);

    }



}
