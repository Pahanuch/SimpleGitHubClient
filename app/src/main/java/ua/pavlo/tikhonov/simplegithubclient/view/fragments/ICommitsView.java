package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Commits;

public interface ICommitsView extends IView {

    void showCommits(List<Commits> commitses);

}
