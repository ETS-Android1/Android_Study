package com.example.fcmclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private Button btn_registe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_registe = (Button) findViewById(R.id.btn_registe);
        btn_registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = FirebaseInstanceId.getInstance().getToken();
                if(token != null){
                    Log.d("TOKEN", token);
                    ConnectServerTask serverTask = new ConnectServerTask();
                    serverTask.execute("http://192.168.0.11:3000/register", token);
                }else{
                    Log.w("TOKEN", "NULL");
                }
            }
        });


    }
}