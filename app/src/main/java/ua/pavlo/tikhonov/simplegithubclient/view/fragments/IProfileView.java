package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;

public interface IProfileView extends IView {

    void startRepoListFragment(User user);

    void showUserInfo(User user);

    void onStartLogin();
}
