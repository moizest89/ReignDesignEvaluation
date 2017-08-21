package moizest89.reigndesignevaluation.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import moizest89.reigndesignevaluation.data.models.HighlightResult;
import moizest89.reigndesignevaluation.data.models.Hit;
import moizest89.reigndesignevaluation.data.models.StoryTitle;
import moizest89.reigndesignevaluation.data.models.Title;

/**
 * Created by moizest89 on 8/15/17.
 */

public class Util {

    public final static int ANIMAATION_DATA_DELAY = 700;
    public final static String INTENT_DATA_SEND = "data";
    private final static String TAG = Util.class.getSimpleName();


    public static void changeActivity(Context context, Class<?> target, Bundle options, boolean finish) {
        Intent i = new Intent(context, target);

        if (options != null)
            i.putExtras(options);

        context.startActivity(i);

        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        if (finish)
            ((Activity) context).finish();

    }


    public static String validateNullString(String value){

        if(value == null){
            return "";
        }
        return value;
    }


    /** I created two ways to get title because "the _highlightResult"
     * key sometimes has title or story_title values
     */

    public static String getHitTitle(Hit hit){


        String mTitleArticle = "";
        HighlightResult highlightResult = hit.getHighlightResult();
        if(highlightResult.getTitle() != null) {
            Title title = highlightResult.getTitle();
            mTitleArticle = title.getValue();
        }else {
            StoryTitle storyTitle = highlightResult.getStoryTitle();
            mTitleArticle = storyTitle.getValue();
        }

        Log.e(TAG, "mTitleArticle: "+mTitleArticle);

        return mTitleArticle;
    }


    public static String getHitUrl(Hit hit){
        String mUrlWebSite = "http://google.com/webiste="+hit.getStoryTitle();

        if(hit.getUrl() == null){
            if(hit.getStoryUrl() != null){
                mUrlWebSite = hit.getStoryUrl();
            }
        }else{
            mUrlWebSite = hit.getUrl();
        }

        return mUrlWebSite;
    }
}
