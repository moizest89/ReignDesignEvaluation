package moizest89.reigndesignevaluation.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by moizest89 on 8/14/17.
 */

public class CommentText implements Parcelable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("fullyHighlighted")
    @Expose
    private Boolean fullyHighlighted;
    @SerializedName("matchedWords")
    @Expose
    private List<String> matchedWords = null;

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

    public Boolean getFullyHighlighted() {
        return fullyHighlighted;
    }

    public void setFullyHighlighted(Boolean fullyHighlighted) {
        this.fullyHighlighted = fullyHighlighted;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<String> matchedWords) {
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
        dest.writeValue(this.fullyHighlighted);
        dest.writeStringList(this.matchedWords);
    }

    public CommentText() {
    }

    protected CommentText(Parcel in) {
        this.value = in.readString();
        this.matchLevel = in.readString();
        this.fullyHighlighted = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.matchedWords = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CommentText> CREATOR = new Parcelable.Creator<CommentText>() {
        public CommentText createFromParcel(Parcel source) {
            return new CommentText(source);
        }

        public CommentText[] newArray(int size) {
            return new CommentText[size];
        }
    };
}
