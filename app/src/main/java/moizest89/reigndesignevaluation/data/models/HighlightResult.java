package moizest89.reigndesignevaluation.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by moizest89 on 8/14/17.
 */

public class HighlightResult {

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("comment_text")
    @Expose
    private CommentText commentText;
    @SerializedName("story_title")
    @Expose
    private StoryTitle storyTitle;
    @SerializedName("story_url")
    @Expose
    private StoryUrl storyUrl;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public CommentText getCommentText() {
        return commentText;
    }

    public void setCommentText(CommentText commentText) {
        this.commentText = commentText;
    }

    public StoryTitle getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(StoryTitle storyTitle) {
        this.storyTitle = storyTitle;
    }

    public StoryUrl getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(StoryUrl storyUrl) {
        this.storyUrl = storyUrl;
    }
}
