package com.example.abhishek.weatherforecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by abhishek on 30/7/18.
 */

public class NetworkConnectivityReceiver extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;

    public NetworkConnectivityReceiver(){

    }
/*

    public NetworkConnectivityReceiver(ConnectivityReceiverListener connectivityReceiverListener) {
        this.connectivityReceiverListener = connectivityReceiverListener;
    }
*/

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isConnected = isConnected();
        if(MyApplication.getInstance().lastState!=null && MyApplication.getInstance().lastState==isConnected)
            return;

        MyApplication.getInstance().lastState=isConnected;

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }

    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
