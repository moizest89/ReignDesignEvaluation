package moizest89.reigndesignevaluation.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import moizest89.reigndesignevaluation.ui.util.NetworkUtils;

/**
 * Created by moizest89 on 8/14/17.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(!NetworkUtils.isNetworkAvailable(this)){
            Toast.makeText(this, "No network available!", Toast.LENGTH_SHORT).show();
        }

    }
}
