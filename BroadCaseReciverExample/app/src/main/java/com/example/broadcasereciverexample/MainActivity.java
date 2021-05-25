package com.example.broadcasereciverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView tv_state;
    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_state = (TextView) findViewById(R.id.tv_state);
        
        //Registration BroadCastReceiver
        IntentFilter filter = new IntentFilter();   //확실하게 명시하지 않음 > IntentFilter > 만들어 두었으니 잘 사용해라
                                                    //명시적으로 Intent 사용 > Intent > 이거 만들었으니 이거를 사용해라
        receiver = new NetworkReceiver();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);     //어떤 용도로 사용할지 Action를 추가해줌
        registerReceiver(receiver, filter);     //Start NetworkReceiver.java


    }
}