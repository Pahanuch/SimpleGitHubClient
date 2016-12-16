
package ua.pavlo.tikhonov.simplegithubclient.model.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommitDTO {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("commit")
    @Expose
    private Commit_DTO commit;
    @SerializedName("author")
    @Expose
    private Author_DTO author;
    @SerializedName("committer")
    @Expose
    private Committer_DTO committer;
    @SerializedName("parents")
    @Expose
    private List<ParentDTO> parents = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Commit_DTO getCommit() {
        return commit;
    }

    public void setCommit(Commit_DTO commit) {
        this.commit = commit;
    }

    public Author_DTO getAuthor() {
        return author;
    }

    public void setAuthor(Author_DTO author) {
        this.author = author;
    }

    public Committer_DTO getCommitter() {
        return committer;
    }

    public void setCommitter(Committer_DTO committer) {
        this.committer = committer;
    }

    public List<ParentDTO> getParents() {
        return parents;
    }

    public void setParents(List<ParentDTO> parents) {
        this.parents = parents;
    }

}





