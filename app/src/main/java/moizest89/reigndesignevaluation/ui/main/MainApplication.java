package moizest89.reigndesignevaluation.ui.main;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by moizest89 on 8/16/17.
 */

public class MainApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

    }
}
