package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Branch;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Contributor;

public interface IRepoInfoView extends IView {

    void showContributors(List<Contributor> contributors);

    void showBranches(List<Branch> branches);

}
