package ua.pavlo.tikhonov.simplegithubclient.presenter.vo;

import java.io.Serializable;

public class Commits implements Serializable {
    private String hash;
    private String shortCommitMessage;
    private String author;
    private String date;

    public Commits(String hash, String shortCommitMessage, String author, String date) {
        this.hash = hash;
        this.shortCommitMessage = shortCommitMessage;
        this.author = author;
        this.date = date;
    }

    public String getHash() {
        return hash;
    }

    public String getShortCommitMessage() {
        return shortCommitMessage;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commits that = (Commits) o;

        if (hash != null ? !hash.equals(that.hash) : that.hash != null)
            return false;
        return !(shortCommitMessage != null ? !shortCommitMessage.equals(that.shortCommitMessage) : that.shortCommitMessage != null);

    }

    @Override
    public int hashCode() {
        int result = hash != null ? hash.hashCode() : 0;
        result = 31 * result + (shortCommitMessage != null ? shortCommitMessage.hashCode() : 0);
        return result;
    }
}
