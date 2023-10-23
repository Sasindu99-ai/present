package com.vvecon.present.Core;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

import com.vvecon.present.Services.NotificationService;

public class Activity extends AppCompatActivity {
    private NotificationService notificationService;
    private boolean isNotificationServiceBound = false;

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private final ServiceConnection notificationServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NotificationService.notificationBinder binder = (NotificationService.notificationBinder) iBinder;
            notificationService = binder.getService();
            isNotificationServiceBound = true;
            // Perform any additional setup if needed
            notificationService.setActivity(Activity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            notificationService = null;
            isNotificationServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start the NotificationService if it's not already running
        if (!isServiceRunning(NotificationService.class)) {
            Intent notificationServiceIntent = new Intent(this, NotificationService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startService(notificationServiceIntent);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the NotificationService if it's running and not already bound
        if (isServiceRunning(NotificationService.class) && !isNotificationServiceBound) {
            Intent notificationServiceIntent = new Intent(this, NotificationService.class);
            bindService(notificationServiceIntent, notificationServiceConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the NotificationService if bound
        if (isNotificationServiceBound) {
            unbindService(notificationServiceConnection);
            isNotificationServiceBound = false;
        }
    }
}

