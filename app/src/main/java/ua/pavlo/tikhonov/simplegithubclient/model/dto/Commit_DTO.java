
package ua.pavlo.tikhonov.simplegithubclient.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commit_DTO {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private AuthorDTO author;
    @SerializedName("committer")
    @Expose
    private CommitterDTO committer;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tree")
    @Expose
    private TreeDTO tree;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("verification")
    @Expose
    private VerificationDTO verification;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public CommitterDTO getCommitter() {
        return committer;
    }

    public void setCommitter(CommitterDTO committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TreeDTO getTree() {
        return tree;
    }

    public void setTree(TreeDTO tree) {
        this.tree = tree;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public VerificationDTO getVerification() {
        return verification;
    }

    public void setVerification(VerificationDTO verification) {
        this.verification = verification;
    }

}
