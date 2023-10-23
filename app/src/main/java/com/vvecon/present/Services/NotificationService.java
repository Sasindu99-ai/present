package com.vvecon.present.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vvecon.present.Controller.UserController;
import com.vvecon.present.Core.Activity;
import com.vvecon.present.Core.Util;
import com.vvecon.present.R;
import com.vvecon.present.View.MainActivity;

public class NotificationService extends Service {
    private Util UTIL;
    private UserController CONTROLLER;
    private JsonObject USER;
    private Boolean isRunning = false;
    private final Handler handler = new Handler();
    private final Runnable notificationRunnable = this::notifications;

    public NotificationService() {
        super();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                NotificationChannel channel = new NotificationChannel(
                        "General",
                        "General",
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                NotificationManager manager = getSystemService(NotificationManager.class);
                if (manager != null) {
                    manager.createNotificationChannel(channel);
                }
            } catch (RuntimeException ignored) {}
        }
    }

    private void showNotification(Integer notificationId, String Title, String Message, PendingIntent pendingIntent) {
        createNotificationChannels();
        try {
            Notification notification = new NotificationCompat.Builder(this, "General")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(Title)
                    .setContentText(Message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)
                    .build();

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.notify(notificationId, notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class notificationBinder extends Binder {
        public NotificationService getService() {
            return NotificationService.this;
        }
    }

    private final IBinder binder = new notificationBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setActivity(Activity activity) {
        UTIL = new Util(activity);
        // USER = UTIL.getUser();
        CONTROLLER = new UserController(activity);

        if (!isRunning) {
            handler.postDelayed(notificationRunnable, 1000);
        }
    }

    private void notifications() {
        isRunning = true;

        Integer userId = UTIL.getAsInteger(USER.get("id"));
        if (userId != null) {
            CONTROLLER.notifications(userId, this::onNotifications);
        } else {
            handler.postDelayed(notificationRunnable, 1000);
        }
    }

    private void onNotifications(JsonObject api_report) {
        if (api_report != null) {
            JsonArray result = UTIL.parseArray(api_report);
            if (result != null) {
                for (JsonElement notificationElement : result) {
                    JsonObject notification = notificationElement.getAsJsonObject();
                    showNotification(notification);
                }
            }
        }
        handler.postDelayed(notificationRunnable, 1000);
    }

    private void showNotification(JsonObject notification) {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setAction("New Task");
            int requestCode = (int) System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);

            showNotification(notification.get("id").getAsInt(), UTIL.getAsString(notification.get("title")), UTIL.getAsString(notification.get("message")), pendingIntent);
        } catch (IllegalArgumentException ignored) {}
    }
}
