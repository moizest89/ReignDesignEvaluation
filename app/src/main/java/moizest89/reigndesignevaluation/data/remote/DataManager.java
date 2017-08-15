package moizest89.reigndesignevaluation.data.remote;

import android.content.Context;

import moizest89.reigndesignevaluation.data.models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by moizest89 on 8/14/17.
 */

public class DataManager {


    private ApiService apiService;
    private Context context;


    public DataManager(Context context) {
        this.context = context;

        this.apiService = ReignDesigneClient.getClient();

    }


    public void getArticles(final DataManagerCallBacks dataManagerCallBacks){

        Call<UserResponse> call = this.apiService.getArticlesList();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {


//                if(response.isSuccessful()){
                UserResponse userResponse = response.body();
                dataManagerCallBacks.onSuccess(response);
//                }else{
//
//                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                dataManagerCallBacks.onError(t);
            }
        });

    }




    public interface DataManagerCallBacks<T>{
        void onSuccess( T onSucces);
        void onError(Throwable onError);
    }

}
