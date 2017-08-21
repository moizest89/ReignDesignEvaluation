package moizest89.reigndesignevaluation.ui.article.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
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
    private Hit hit;

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

             this.hit = this.realm.where(Hit.class).equalTo("objectID", this.object_id).findFirst();

            if(hit.isLoaded()) {
                getSupportActionBar().setTitle(R.string.title_activity_article_details);
                getSupportActionBar().setSubtitle(Util.getHitTitle(hit));

                String mUrlWebSite = Util.getHitUrl(this.hit);

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
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share_item, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_share_info) {
            shareInfo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareInfo(){

        if(hit.isLoaded()) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = Util.getHitTitle(hit) +
                    " "+Util.getHitUrl(this.hit)+
                    " \n "+getResources().getString(R.string.main_message_share_via_app);
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Util.getHitTitle(hit));
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }

    }

}
