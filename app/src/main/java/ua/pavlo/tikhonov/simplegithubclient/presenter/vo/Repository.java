package ua.pavlo.tikhonov.simplegithubclient.presenter.vo;

import java.io.Serializable;

public class Repository implements Serializable {
    private String repoName;
    private String ownerName;
    private String avatar;
    private String description;
    private int forksCount;
    private int watchesCount;

    public Repository(String repoName, String ownerName, String avatar, String description, int forksCount, int watchesCount) {
        this.repoName = repoName;
        this.ownerName = ownerName;
        this.avatar = avatar;
        this.description = description;
        this.forksCount = forksCount;
        this.watchesCount = watchesCount;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getForksCount() {
        return forksCount;
    }

    public int getWatchesCount() {
        return watchesCount;
    }

    public String getDescription() {
        return description;
    }

    public String getAvatar(){
        return avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repository that = (Repository) o;

        if (repoName != null ? !repoName.equals(that.repoName) : that.repoName != null)
            return false;
        return !(ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null);

    }

    @Override
    public int hashCode() {
        int result = repoName != null ? repoName.hashCode() : 0;
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }
}
