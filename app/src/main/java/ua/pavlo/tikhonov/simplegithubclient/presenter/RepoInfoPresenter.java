package ua.pavlo.tikhonov.simplegithubclient.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.mappers.RepoBranchesMapper;
import ua.pavlo.tikhonov.simplegithubclient.presenter.mappers.RepoContributorsMapper;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Branch;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Contributor;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IRepoInfoView;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IView;

public class RepoInfoPresenter extends BasePresenter {

    private static final String BUNDLE_BRANCHES_KEY = "BUNDLE_BRANCHES_KEY";
    private static final String BUNDLE_CONTRIBUTORS_KEY = "BUNDLE_CONTRIBUTORS_KEY";
    private static final int COUNT_SUBSCRIPTION = 2;

    @Inject
    protected RepoBranchesMapper branchesMapper;

    @Inject
    protected RepoContributorsMapper contributorsMapper;

    private int countCompletedSubscription = 0;

    private IRepoInfoView view;

    private List<Contributor> contributorList;
    private List<Branch> branchList;

    private Repository repository;

    private void loadData() {
        String owner = repository.getOwnerName();
        String name = repository.getRepoName();

        showLoadingState();
        Subscription subscriptionBranches = model.getRepoBranches(owner, name)
                .map(branchesMapper)
                .subscribe(new Observer<List<Branch>>() {
                    @Override
                    public void onCompleted() {
                        hideInfoLoadingState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideInfoLoadingState();
                        showError(e);
                    }

                    @Override
                    public void onNext(List<Branch> list) {
                        branchList = list;
                        view.showBranches(list);
                    }
                });
        addSubscription(subscriptionBranches);

        Subscription subscriptionContributors = model.getRepoContributors(owner, name)
                .map(contributorsMapper)
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onCompleted() {
                        hideInfoLoadingState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideInfoLoadingState();
                        showError(e);
                    }

                    @Override
                    public void onNext(List<Contributor> list) {
                        contributorList = list;
                        view.showContributors(list);
                    }
                });

        addSubscription(subscriptionContributors);
    }

    public void onCreate(IRepoInfoView view, Repository repository) {
        App.getComponent().inject(this);
        this.view = view;
        this.repository = repository;
    }

    protected void hideInfoLoadingState() {
        countCompletedSubscription++;

        if (countCompletedSubscription == COUNT_SUBSCRIPTION) {
            hideLoadingState();
            countCompletedSubscription = 0;
        }
    }

    public void onCreateView(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            contributorList = (List<Contributor>) savedInstanceState.getSerializable(BUNDLE_CONTRIBUTORS_KEY);
            branchList = (List<Branch>) savedInstanceState.getSerializable(BUNDLE_BRANCHES_KEY);
        }

        if (contributorList == null || branchList == null) {
            loadData();
        } else {
            view.showBranches(branchList);
            view.showContributors(contributorList);
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        if (contributorList != null)
            outState.putSerializable(BUNDLE_CONTRIBUTORS_KEY, new ArrayList<>(contributorList));
        if (branchList != null)
            outState.putSerializable(BUNDLE_BRANCHES_KEY, new ArrayList<>(branchList));

    }

    @Override
    protected IView getView() {
        return view;
    }
}
