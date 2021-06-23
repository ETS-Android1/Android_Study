package com.example.push_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "TAG";
    FirebaseMessaging firebaseMessaging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.setAutoInitEnabled(true);
        firebaseMessaging.getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(!task.isSuccessful()){
                            Log.w(TAG, "Fetching FCM registrationtoken failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.v("token", "token : " + token);

                        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}