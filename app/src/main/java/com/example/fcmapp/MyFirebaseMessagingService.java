package com.example.fcmapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle FCM messages here
        if (remoteMessage.getData().size() > 0) {
            // Extract and handle the message data
            String messageData = remoteMessage.getData().toString();
            sendNotification("New message", messageData);
        }

        if (remoteMessage.getNotification() != null) {
            // Extract and handle the notification message
            String notificationBody = remoteMessage.getNotification().getBody();
            sendNotification("FCM Notification", notificationBody);
        }
    }

    @Override
    public void onNewToken(String token) {
        // This method is called whenever a new token is generated
        // You can send the token to your server for push notifications
        System.out.println("New FCM Token: " + token);
    }

    private void sendNotification(String title, String messageBody) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "default_channel";

        // For Android O and above, create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Build and show the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)  // Set your notification icon
                .setContentTitle(title)                     // Set the title of the notification
                .setContentText(messageBody)                // Set the body of the notification
                .setAutoCancel(true);                       // Auto-cancel the notification when tapped

        notificationManager.notify(0, notificationBuilder.build());
    }
}
