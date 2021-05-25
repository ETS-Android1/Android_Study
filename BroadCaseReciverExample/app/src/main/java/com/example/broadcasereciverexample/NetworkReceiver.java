package com.example.broadcasereciverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get Network State
        if(WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())){
            NetworkInfo info = (NetworkInfo) intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            NetworkInfo.DetailedState state = info.getDetailedState();
            if(state == NetworkInfo.DetailedState.CONNECTED){   //Connect State
                MainActivity.tv_state.setText("Connecting Network");
            }
            else if(state == NetworkInfo.DetailedState.DISCONNECTED){   //DisConnect State
                MainActivity.tv_state.setText("Disconnecting Network");
            }
        }
    }
}
