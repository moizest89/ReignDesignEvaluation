package moizest89.reigndesignevaluation.ui.base;

/**
 * Created by moizest89 on 8/14/17.
 */

public interface Presenter <V extends MvpView>{

    void attachView(V mvpView);

    void detachView();
}
