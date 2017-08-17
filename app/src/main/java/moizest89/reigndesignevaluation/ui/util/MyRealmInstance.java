package moizest89.reigndesignevaluation.ui.util;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by moizest89 on 8/16/17.
 */

public class MyRealmInstance {

    private static Realm realm;

    public static Realm with(Context context){
        if(realm == null){
            Realm.init(context);
            realm = Realm.getDefaultInstance();
        }
        return realm;
    }

    private MyRealmInstance() {
        throw new AssertionError("No instances.");
    }

}
