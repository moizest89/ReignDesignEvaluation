package moizest89.reigndesignevaluation.ui.splash;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.ui.main.MainActivity;
import moizest89.reigndesignevaluation.ui.util.Util;

public class SplashActivity extends AppCompatActivity {

    private long splashDelay = 2000; //2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        setTime();

    }

    public void setTime(){

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Util.changeActivity(SplashActivity.this, MainActivity.class, null, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, splashDelay);

    }

}
