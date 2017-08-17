package moizest89.reigndesignevaluation.data.remote;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmResults;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.data.models.UserResponse;
import moizest89.reigndesignevaluation.ui.util.MyRealmInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by moizest89 on 8/14/17.
 */

public class DataManager {


    private ApiService apiService;
    private Context context;
    private Realm realm;


    public DataManager(Context context) {
        this.context = context;
        this.apiService = ReignDesigneClient.getClient();
        this.realm = MyRealmInstance.with(this.context);

    }


    public void updateArticles(final DataManagerCallBacks dataManagerCallBacks){

        this.realm.beginTransaction();
        this.realm.delete(UserResponse.class);
        this.realm.commitTransaction();

        getArticles(dataManagerCallBacks);
//        this.realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.deleteAll();
//
//            }
//        });
    }

    public void getArticles(final DataManagerCallBacks dataManagerCallBacks){


        final UserResponse result = realm.where(UserResponse.class).findFirst();

        if(result == null) {
            Call<UserResponse> call = this.apiService.getArticlesList();
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, final Response<UserResponse> response) {


                    if(response.isSuccessful()){

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                UserResponse userResponse = response.body();
                                realm.copyToRealmOrUpdate(userResponse);
                                dataManagerCallBacks.onSuccess(response.body());
                            }
                        });

                    }else{

                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    dataManagerCallBacks.onError(t);
                }
            });
        }else{
            dataManagerCallBacks.onSuccess(result);
        }



    }

    public void deleteHitItem(final String objectId, final DataManagerCallBacks dataManagerCallBacks){

        if(this.realm !=null){

            this.realm.executeTransactionAsync(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    Hit hit = realm.where(Hit.class).equalTo("objectID",objectId).findFirst();
                    if(hit != null) {
                        hit.deleteFromRealm();
                    }
                }

            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    dataManagerCallBacks.onSuccess(null);
                }
            });
        }

    }



    public interface DataManagerCallBacks<T>{
        void onSuccess( T onSucces);
        void onError(Throwable onError);
    }

}
