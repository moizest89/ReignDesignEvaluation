package moizest89.reigndesignevaluation.ui.main;

import android.app.Application;

import io.realm.Realm;
import moizest89.reigndesignevaluation.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by moizest89 on 8/16/17.
 */

public class MainApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto_condensed.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
