package moizest89.reigndesignevaluation.ui.article.list;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.data.models.UserResponse;
import moizest89.reigndesignevaluation.ui.article.details.ArticleDetailsActivity;
import moizest89.reigndesignevaluation.ui.main.MainActivity;
import moizest89.reigndesignevaluation.ui.util.NetworkUtils;
import moizest89.reigndesignevaluation.ui.util.OnItemClickListener;
import moizest89.reigndesignevaluation.ui.util.RecyclerViewItemAction;
import moizest89.reigndesignevaluation.ui.util.Util;


public class ArticleListFragment extends Fragment implements
        IArticleListView,
        RecyclerViewItemAction.RecyclerViewItemActionCallBacks, // Custom interface to get swipe recyclerview actions
        SwipeRefreshLayout.OnRefreshListener // Pull to refresh actions
        {


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
        this.mAdapater.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Integer position) {
                Hit hit = mAdapater.getDataForValue(position);

                Bundle bundle = new Bundle();
                bundle.putString(Util.INTENT_DATA_SEND, hit.getObjectID());

                Util.changeActivity(getContext(), ArticleDetailsActivity.class, bundle, false);

            }
        });

        this.swipeRefreshLayout.setOnRefreshListener(this);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.mAdapater);


        //Swipe to delete
        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerViewItemAction(getActivity(),this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(this.recyclerView);

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
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

        this.mAdapater.setmData(response.getHits());
    }

    @Override
    public void showSimpleMessage(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteItemFromList(int position) {
        this.mAdapater.notifyItemRemoved(position);
    }



    /**
     * RecyclerView swipe interface
     * */
    @Override
    public void itemIsDeleted(boolean status, int position) {
        if(status){
            mPresenter.deleteSpecificItemFromList(mAdapater.getDataForValue(position), position);
        }else{
            mAdapater.itemNotRemoved(position);
        }
    }


    /**
    * Pull to refresh
    */

    @Override
    public void onRefresh() {

//        NetworkUtils.hasInternetConnection(getActivity(), new NetworkUtils.NetworkUtilsInterface() {
//            @Override
//            public void hasConnection(boolean status) {
//                if(status){
                    mPresenter.updateData();
//                }else{
//                    if(swipeRefreshLayout.isRefreshing())
//                        swipeRefreshLayout.setRefreshing(false);
//                    showSimpleMessage(R.string.main_message_no_internet_connection);
//                }
//            }
//        });
    }
}
