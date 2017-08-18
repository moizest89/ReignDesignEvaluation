package moizest89.reigndesignevaluation.ui.article.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.ui.util.MyRealmInstance;
import moizest89.reigndesignevaluation.ui.util.Util;

public class ArticleDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;


    private String object_id;
    private Realm realm;

    private final static String TAG = ArticleDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ButterKnife.bind(this);

        setToolbar();

        Intent intent = getIntent();
        if(intent != null){
            this.object_id = intent.getStringExtra(Util.INTENT_DATA_SEND);
            this.realm = MyRealmInstance.with(this);

            Hit hit = this.realm.where(Hit.class).equalTo("objectID", this.object_id).findFirst();

            if(hit.isLoaded()) {

                getSupportActionBar().setTitle(Util.getHitTitle(hit));

                String mUrlWebSite = "http://google.com/webiste="+hit.getStoryTitle();

                if(hit.getUrl() == null){
                    if(hit.getStoryUrl() != null){
                        mUrlWebSite = hit.getStoryUrl();
                    }
                }else{
                    mUrlWebSite = hit.getUrl();
                }

                Log.e(TAG, "mUrlWebSite: "+mUrlWebSite);

                this.web_view.loadUrl(mUrlWebSite);
                this.web_view.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        progress_bar.animate().alpha(0).setDuration(Util.ANIMAATION_DATA_DELAY);
                        view.animate().alpha(1).setDuration(Util.ANIMAATION_DATA_DELAY);
                    }
                });
            }else{
                Toast.makeText(this, "Error to load Data", Toast.LENGTH_SHORT).show();
            }
        }

    }



    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
