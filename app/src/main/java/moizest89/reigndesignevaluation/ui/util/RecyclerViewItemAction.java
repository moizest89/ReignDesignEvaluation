package moizest89.reigndesignevaluation.ui.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by moizest89 on 8/15/17.
 */

public class RecyclerViewItemAction extends ItemTouchHelper.SimpleCallback {


    private Context context;
    private  RecyclerViewItemActionCallBacks mCallbackActions;

    public RecyclerViewItemAction(Context context, RecyclerViewItemActionCallBacks callBacks) {
        super(0, ItemTouchHelper.LEFT);
        this.context = context;
        this.mCallbackActions = callBacks;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        final int position = viewHolder.getAdapterPosition();

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage("Are you sure to delete?");    //set message

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { //when click on DELETE
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCallbackActions.itemIsDeleted(true, position);
                return;
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {  //not removing items if cancel is done
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCallbackActions.itemIsDeleted(false, position);
                return;
            }
        }).show();  //show alert dialog

    }

    public interface RecyclerViewItemActionCallBacks{
        void itemIsDeleted(boolean status, int position);
    }
}
