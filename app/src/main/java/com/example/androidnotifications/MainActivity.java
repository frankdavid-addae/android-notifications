package com.example.androidnotifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnShowNotification;

    private static final String CHANNEL_ID = "some_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowNotification = findViewById(R.id.btnShowNotification);

        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                setUpNotification();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUpNotification() {
        @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Notification 1", NotificationManager.IMPORTANCE_MAX);

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle("Notification Title")
                .setContentText("Body of the notification.")
//                .setStyle(new Notification.BigTextStyle().bigText("Body of the notification that is very big......................................"))
//                .setOngoing(true) // Prevents dismissing of notification
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.img)).bigLargeIcon((Bitmap) null))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img))
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 1, intent, 0))
                .setSmallIcon(R.drawable.ic_adb)
                .setChannelId(CHANNEL_ID);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1, builder.build());
    }
}