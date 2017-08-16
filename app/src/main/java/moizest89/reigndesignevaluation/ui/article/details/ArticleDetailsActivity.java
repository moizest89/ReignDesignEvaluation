package moizest89.reigndesignevaluation.ui.article.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.Hit;

public class ArticleDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;


    private Hit hit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        if(intent != null){
            
        }

    }

}
