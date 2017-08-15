package moizest89.reigndesignevaluation.ui.article.list;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.UserResponse;
import moizest89.reigndesignevaluation.ui.util.Util;


public class ArticleListFragment extends Fragment implements IArticleListView{


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private ArticleListPresenter mPresenter;
    private ArticleListAdapter mAdapater;


    public ArticleListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        ButterKnife.bind(this, view);

        this.mAdapater = new ArticleListAdapter(getActivity());

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.mAdapater);

        this.mPresenter = new ArticleListPresenter(getActivity());
        this.mPresenter.attachView(this);
        this.mPresenter.getData();

        return view;
    }

    @Override
    public void showLoader(boolean status) {

        if(status){
            this.progressBar.animate().alpha(1).setDuration(Util.ANIMAATION_DATA_DELAY);
        }else{
            this.progressBar.animate().alpha(0).setDuration(Util.ANIMAATION_DATA_DELAY);
        }
    }

    @Override
    public void showData(boolean status) {
        if(status){
            this.swipeRefreshLayout.animate().alpha(1).setDuration(Util.ANIMAATION_DATA_DELAY);
        }else{
            this.swipeRefreshLayout.animate().alpha(0).setDuration(Util.ANIMAATION_DATA_DELAY);
        }
    }

    @Override
    public void setData(UserResponse response) {
        this.mAdapater.setmData(response.getHits());
    }
}
