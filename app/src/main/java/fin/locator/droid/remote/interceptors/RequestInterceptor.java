package fin.locator.droid.remote.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder request = original.newBuilder()
                .header("Accept","application/json")
                .header("Content-type","application/json")
                .method(original.method(),original.body());

        Request builder = request.build();
        return chain.proceed(builder);
    }
}
