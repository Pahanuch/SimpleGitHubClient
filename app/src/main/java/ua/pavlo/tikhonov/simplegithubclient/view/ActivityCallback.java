package ua.pavlo.tikhonov.simplegithubclient.view;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;

public interface ActivityCallback {

    void startLoginFragment();

    void startProfileFragment();

    void startRepoListFragment(User user);

    void startRepoInfoFragment(Repository repository);

    void startCommitsFragment(Repository repository);

    void showProgressBar();

    void hideProgressBar();

    void setTitle(String title, boolean addBackButton);

}
