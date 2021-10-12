package com.example.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView iv_profile;
    TextView tv_displayName;
    FirebaseApp app;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        tv_displayName = (TextView) findViewById(R.id.tv_displayName);

        if(user != null){
            Log.d("USER INFO", user.getUid());
            Toast.makeText(this, user.getUid(), Toast.LENGTH_LONG).show();
            tv_displayName.setText(user.getUid());
            Glide.with(this).load(user.getPhotoUrl()).into(iv_profile);
        }else{
            Log.w("USER INFO", "Error");
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            tv_displayName.setText("error");
        }

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                createNotificationChannel("ch1", "default channel", NotificationManager.IMPORTANCE_HIGH);
                createNotification("ch1", 1, "title", "content text", intent);
                Toast.makeText(getApplicationContext(), "알림 보냈음", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void updateUi(FirebaseUser user){
        tv_displayName.setText(user.getUid());
    }

    void createNotificationChannel(String channelId, String channelName, int importance){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, importance));
        }
    }

    void createNotification(String channelId, int id, String title, String text, Intent intent){
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    //Why it hear?
    void destroyNotification(int id){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }
}

class GetUserFromFirebase extends AsyncTask<FirebaseUser, FirebaseUser, FirebaseUser> {

    @Override
    protected FirebaseUser doInBackground(FirebaseUser... firebaseUsers) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        return user;
    }

    @Override
    protected void onPostExecute(FirebaseUser user) {
        super.onPostExecute(user);
        Log.d("onPostExecute", user.getUid());
    }
}