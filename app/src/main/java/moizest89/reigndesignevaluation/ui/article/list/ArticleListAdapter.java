package moizest89.reigndesignevaluation.ui.article.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import moizest89.reigndesignevaluation.R;
import moizest89.reigndesignevaluation.data.models.HighlightResult;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.data.models.StoryTitle;
import moizest89.reigndesignevaluation.data.models.Title;
import moizest89.reigndesignevaluation.ui.util.OnItemClickListener;
import moizest89.reigndesignevaluation.ui.util.Util;

/**
 * Created by moizest89 on 8/15/17.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.Holder>{

    private Context context;
    private RealmList<Hit> mData;
    private OnItemClickListener onItemClickListener;

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

        Hit hit = this.mData.get(position);

        //Title
        String mTitleArticle = Util.getHitTitle(hit);
        holder.text_view_title.setText(Html.fromHtml(Util.validateNullString(mTitleArticle)));

        //Content
//        holder.text_view_content.setText((Util.validateNullString(hit.getCommentText())));

        //Author and dat

        holder.text_view_author_and_date.setText((Util.validateNullString(hit.getAuthor())));


    }

    @Override
    public int getItemCount() {
        return ((this.mData == null)? 0 : this.mData.size() );
    }

    public void setmData(RealmList<Hit> data){
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public Hit getDataForValue(int position){
        return this.mData.get(position);
    }

    public void itemNotRemoved(int position){
        this.notifyItemChanged(position);
    }

    public void removeItem(int position){
        this.mData.remove(position);
        this.notifyItemRemoved(position);

    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.text_view_title)
        TextView text_view_title;
//        @BindView(R.id.text_view_content)
//        TextView text_view_content;
        @BindView(R.id.text_view_author_and_date)
        TextView text_view_author_and_date;


        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.onItemClickListener = clickListener;
    }
}
