package moizest89.reigndesignevaluation.ui.article.list;

import moizest89.reigndesignevaluation.data.models.UserResponse;
import moizest89.reigndesignevaluation.ui.base.MvpView;

/**
 * Created by moizest89 on 8/14/17.
 */

public interface IArticleListView extends MvpView{

    void showLoader(boolean status);
    void showData(boolean status);
    void setData(UserResponse response);
    void showSimpleMessage(int message);
    void deleteItemFromList(int position);

}
