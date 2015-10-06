package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Onculy on 6.10.2015.
 */
public class AlarmService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent ıntent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }
}
