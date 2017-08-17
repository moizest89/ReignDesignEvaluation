package moizest89.reigndesignevaluation.ui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by moizest89 on 8/15/17.
 */

public class NetworkUtils {

    private final static String TAG = NetworkUtils.class.getName();

    public static void hasInternetConnection(Context context, final NetworkUtilsInterface utilsInterface) {
        if (isNetworkAvailable(context)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        HttpURLConnection urlc = (HttpURLConnection) new URL("http://clients3.google.com/generate_204").openConnection();
                        urlc.setRequestProperty("User-Agent", "Android");
                        urlc.setRequestProperty("Connection", "close");
                        urlc.setConnectTimeout(1500);
                        urlc.connect();
                        utilsInterface.hasConnection(urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);

                    } catch (IOException e) {
                        Log.e(TAG, "Error checking internet connection", e);
                        utilsInterface.hasConnection(false);
                    }
                }
            }).start();

        } else {

            Log.d(TAG, "No network available!");
            utilsInterface.hasConnection(false);

        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


    public interface NetworkUtilsInterface{
        void hasConnection(boolean status);
    }
}
