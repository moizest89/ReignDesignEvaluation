package moizest89.reigndesignevaluation.data.remote;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by moizest89 on 8/14/17.
 */

public class ReignDesigneInterceptor implements Interceptor {


    private final static String LOG = ReignDesigneInterceptor.class.getSimpleName();


    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request response = chain.request();
//
//        Request.Builder builder = chain.request().newBuilder();
////        builder.addHeader("Content-Type", "application/json");
////        builder.addHeader("Authorization:", "Basic cHJ1ZWJhc2RldjpwcnVlYmFzZGV2U2VjcmV0");
//
//
//
//        long t1 = System.nanoTime();
//        Log.i(LOG, String.format("Sending request %s on %s%n%s", builder.url(), chain.connection(), request.headers()));
//
////        Response response = chain.proceed(request);
//
//        long t2 = System.nanoTime();
//        Log.i(LOG,String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//
//
//
////        return chain.proceed(builder.build());
//
//        return response;

        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.i(LOG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.i(LOG,String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
