package moizest89.reigndesignevaluation.ui.article.list;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moizest89.reigndesignevaluation.R;


public class ArticleListFragment extends Fragment implements IArticleListView{


    private ArticleListPresenter mPresenter;


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


        this.mPresenter = new ArticleListPresenter(getActivity());
        this.mPresenter.attachView(this);
        this.mPresenter.getData();

        return view;
    }

}
