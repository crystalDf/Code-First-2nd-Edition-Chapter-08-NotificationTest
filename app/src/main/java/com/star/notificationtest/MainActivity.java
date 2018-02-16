package com.star.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;

    public static final String NOTIFICATION_CHANNEL_ID = "channelId";

    private Button mSendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendNotification = findViewById(R.id.send_notification);
        mSendNotification.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Notification notification = new NotificationCompat.Builder(MainActivity.this, NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(
                            getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setSound(Uri.fromFile(
                            new File("/system/media/audio/ringtones/Luna.ogg")))
                    .setVibrate(new long[] {0, 1000, 1000, 1000})
                    .setLights(Color.GREEN, 1000, 1000)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(
                            "Learn how to build notifications, " +
                                    "send and sync data, and use voice actions. " +
                                    "Get the official Android IDE and developer tools " +
                                    "to build apps for Android."
                    ))
                    .setPriority(Notification.PRIORITY_MAX)
                    .build();

            notificationManager.notify(NOTIFICATION_ID, notification);
        });
    }
}
