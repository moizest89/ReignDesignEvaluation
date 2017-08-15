package moizest89.reigndesignevaluation.ui.article.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.Hit;

/**
 * Created by moizest89 on 8/15/17.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.Holder>{

    private Context context;
    private List<Hit> mData = new ArrayList<>();

    public ArticleListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_article_list, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public void setmData(List<Hit> data){
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public Hit getDataForValue(int position){
        return this.mData.get(position);
    }

    public void removeItem(int position){
        this.mData.remove(position);
        this.notifyItemRemoved(position);

    }


    public class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
