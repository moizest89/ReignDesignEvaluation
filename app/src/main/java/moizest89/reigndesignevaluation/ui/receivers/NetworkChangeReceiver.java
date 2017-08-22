package moizest89.reigndesignevaluation.ui.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by moizest89 on 8/22/17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {


    private final static String TAG = NetworkChangeReceiver.class.getSimpleName();
    private onNetworkChangeStatus onNetworkChangeStatus;

    public NetworkChangeReceiver(onNetworkChangeStatus status) {
        this.onNetworkChangeStatus = status;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            if (isOnline(context)) {
                onNetworkChangeStatus.isNetworkChange(true);
                Log.e(TAG, "Online Connect Intenet ");
            } else {
                onNetworkChangeStatus.isNetworkChange(false);
                Log.e(TAG, "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


    public interface onNetworkChangeStatus{
        void isNetworkChange(boolean status);
    }


    public static class ConnectionExceptions extends Exception{
        public ConnectionExceptions() { super(); }
        public ConnectionExceptions(String message) { super(message); }
    }

}
