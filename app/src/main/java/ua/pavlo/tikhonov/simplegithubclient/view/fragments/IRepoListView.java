package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;

public interface IRepoListView extends IView {

    void showRepoList(List<Repository> vo);

    void showEmptyList();

    String getLogin();

    void startRepoInfoFragment(Repository repository);

    void startCommitsFragment(Repository repository);

}
