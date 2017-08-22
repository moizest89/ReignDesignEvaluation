package moizest89.reigndesignevaluation.ui.article.list;

import android.content.Context;

import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.data.models.UserResponse;
import moizest89.reigndesignevaluation.data.remote.DataManager;
import moizest89.reigndesignevaluation.ui.base.BasePreenter;
import moizest89.reigndesignevaluation.ui.receivers.NetworkChangeReceiver;

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


    public void getData(final boolean isUpdate){

        dataManager.getArticles(isUpdate, new DataManager.DataManagerCallBacks<UserResponse>() {
            @Override
            public void onSuccess(UserResponse onSucces) {

                getMvpView().setData(onSucces);
                getMvpView().showLoader(false);
                getMvpView().showData(true);

            }

            @Override
            public void onError(Throwable onError) {
                getMvpView().showLoader(false);
                getMvpView().showSimpleMessage(R.string.main_message_problems_with_get_data);

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
