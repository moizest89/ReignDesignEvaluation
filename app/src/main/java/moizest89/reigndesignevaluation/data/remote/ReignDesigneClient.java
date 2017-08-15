package moizest89.reigndesignevaluation.data.remote;

import moizest89.reigndesignevaluation.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moizest89 on 8/14/17.
 */

public class ReignDesigneClient {

    public final static String baseURL = BuildConfig.base_url;
    public final static String LOG = ReignDesigneClient.class.getSimpleName();

    private static ApiService apiService;


    public static ApiService  getClient(){

        if(apiService == null){

            OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(new ReignDesigneInterceptor()).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = client.create(ApiService.class);

        }

        return apiService;
    }


}
