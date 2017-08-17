package moizest89.reigndesignevaluation.ui.article.list;

import android.content.Context;

import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.data.models.UserResponse;
import moizest89.reigndesignevaluation.data.remote.DataManager;
import moizest89.reigndesignevaluation.ui.base.BasePreenter;

/**
 * Created by moizest89 on 8/14/17.
 */

public class ArticleListPresenter extends BasePreenter<IArticleListView>{

    private Context context;
    private DataManager dataManager;

    public ArticleListPresenter(Context context) {
        this.context = context;
        this.dataManager = new DataManager(this.context);
    }


    public void getData(){

        dataManager.getArticles(new DataManager.DataManagerCallBacks<UserResponse>() {
            @Override
            public void onSuccess(UserResponse onSucces) {

                getMvpView().setData(onSucces);
                getMvpView().showLoader(false);
                getMvpView().showData(true);

            }

            @Override
            public void onError(Throwable onError) {

            }
        });
    }


    public void updateData(){

        this.dataManager.updateArticles(new DataManager.DataManagerCallBacks<UserResponse>() {
            @Override
            public void onSuccess(UserResponse onSucces) {
                getMvpView().setData(onSucces);
                getMvpView().showLoader(false);
                getMvpView().showData(true);
            }

            @Override
            public void onError(Throwable onError) {

            }
        });

    }
    public void deleteSpecificItemFromList(Hit hit, final int position){

        dataManager.deleteHitItem(hit.getObjectID(), new DataManager.DataManagerCallBacks() {
            @Override
            public void onSuccess(Object onSucces) {

                getMvpView().deleteItemFromList(position);
                getMvpView().showSimpleMessage(R.string.mains_message_delete_item_successful);

            }

            @Override
            public void onError(Throwable onError) {

            }
        });

    }


}
