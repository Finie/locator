package fin.locator.droid.remote;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import fin.locator.droid.remote.interceptors.NetworkConnectionInterceptor;
import fin.locator.droid.remote.interceptors.RequestInterceptor;
import fin.locator.droid.remote.utils.LiveDataCallAdapter;
import fin.locator.droid.remote.utils.LiveDataCallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    /**
     * Logs network requests
     *
     * @return HttpLoggingInterceptor
     */
    private static HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    /**
     * Configure OkHttpClient. This helps us override some of the default configuration. Like the
     * connection timeout.
     *
     * @return OkHttpClient
     */


    private static OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Context mContext) {

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new NetworkConnectionInterceptor(mContext))
                .addInterceptor(new RequestInterceptor())
                .connectTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }


    /**
     * * Creates an instance of retrofit
     *
     * @param baseUrl - url endpoint
     * @return retrofit instance
     */

    private static Retrofit retrofitClient(String baseUrl, Context mContext) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * Creates the client instance
     *Uses the url provided in the ApiConstants
     * @param serviceClass - interface class
     * @param <S>          -interface class
     * @return S
     */

    public static <S> S createService(Class<S> serviceClass, Context mContext){
        return retrofitClient(ApiConstants.BASE_URL,mContext).create(serviceClass);
    }

}
