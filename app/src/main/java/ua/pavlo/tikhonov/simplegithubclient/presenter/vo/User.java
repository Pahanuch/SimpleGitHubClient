package ua.pavlo.tikhonov.simplegithubclient.presenter.vo;

import java.io.Serializable;

/**
 * Created by Tikho on 10.12.2016.
 */

public class User implements Serializable {

    private String login;
    private String avatarUrl;
    private String name;
    private String email;

    public User(String login, String avatarUrl, String name, String email){
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
