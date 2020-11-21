package fin.locator.droid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
    private NetworkUtils() {
        throw new IllegalStateException("Network Util class");
    }


    /**
     * check whether Internet is available or not
     *
     * @param context {@link Context}.
     * @return true if internet is available else false
     */


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
