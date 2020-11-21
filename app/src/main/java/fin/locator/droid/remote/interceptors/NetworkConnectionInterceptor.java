package fin.locator.droid.remote.interceptors;

import android.content.Context;

import java.io.IOException;

import fin.locator.droid.remote.exceptions.NoConnectivityException;
import fin.locator.droid.utils.NetworkUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {

    private Context mContext;

    public NetworkConnectionInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isNetworkAvailable(mContext)) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }


}
