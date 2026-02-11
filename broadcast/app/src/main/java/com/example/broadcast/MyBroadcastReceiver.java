package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    public void onReceive(Context context, Intent intent){
        if(intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
            if (activityNetwork != null && activityNetwork.isConnected()) {
                if (activityNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    Log.d(TAG, "Conectado a Wi-Fi");
                } else if (activityNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Log.d(TAG, "Conectado a red móvil");
                } else {
                    Log.d(TAG, "Conectado a otro tipo de red");
                }
            } else {
                Log.d(TAG, "No hay conexión a Internet");
            }
        }
    }
}
