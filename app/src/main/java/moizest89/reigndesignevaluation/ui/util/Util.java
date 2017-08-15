package moizest89.reigndesignevaluation.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by moizest89 on 8/15/17.
 */

public class Util {

    public final static int ANIMAATION_DATA_DELAY = 700;


    public static void changeActivity(Context context, Class<?> target, Bundle options, boolean finish) {
        Intent i = new Intent(context, target);

        if (options != null)
            i.putExtras(options);

        context.startActivity(i);

        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        if (finish)
            ((Activity) context).finish();

    }
}
