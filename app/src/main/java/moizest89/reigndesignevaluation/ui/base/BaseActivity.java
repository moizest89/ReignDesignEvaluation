package moizest89.reigndesignevaluation.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import moizest89.reigndesignevaluation.ui.util.NetworkUtils;

/**
 * Created by moizest89 on 8/14/17.
 */

public class BaseActivity extends AppCompatActivity {

    public static boolean hasInternetConnection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(!NetworkUtils.isNetworkAvailable(this)){
            hasInternetConnection = false;
        }

    }
}
