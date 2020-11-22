package com.learntodroid.ubereatsandroidclone.loginsignup;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String ORDERS_CHANNEL_ID = "ORDERS_CHANNEL_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel orderNotificationChannel = new NotificationChannel(
                    ORDERS_CHANNEL_ID,
                    "Orders",
                    NotificationManager.IMPORTANCE_HIGH
            );

            orderNotificationChannel.setDescription("Order Progress Updates");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(orderNotificationChannel);
        }
    }
}
