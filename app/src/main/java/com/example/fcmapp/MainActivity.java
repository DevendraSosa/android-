package com.example.fcmapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FirebaseMessaging ka use karein (FirebaseApp pehle hi initialize ho chuka hai)
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        System.out.println("Subscribed to topic!");
                    }
                });
    }
}
