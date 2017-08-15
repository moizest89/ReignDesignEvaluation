package moizest89.reigndesignevaluation.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moizest89 on 8/14/17.
 */

public class StoryTitle implements Parcelable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("matchedWords")
    @Expose
    private List<Object> matchedWords = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public List<Object> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<Object> matchedWords) {
        this.matchedWords = matchedWords;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
        dest.writeString(this.matchLevel);
        dest.writeList(this.matchedWords);
    }

    public StoryTitle() {
    }

    protected StoryTitle(Parcel in) {
        this.value = in.readString();
        this.matchLevel = in.readString();
        this.matchedWords = new ArrayList<Object>();
        in.readList(this.matchedWords, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<StoryTitle> CREATOR = new Parcelable.Creator<StoryTitle>() {
        public StoryTitle createFromParcel(Parcel source) {
            return new StoryTitle(source);
        }

        public StoryTitle[] newArray(int size) {
            return new StoryTitle[size];
        }
    };
}
