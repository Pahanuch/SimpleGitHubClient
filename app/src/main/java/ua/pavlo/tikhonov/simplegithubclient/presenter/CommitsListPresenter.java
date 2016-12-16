package ua.pavlo.tikhonov.simplegithubclient.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.mappers.CommitsListMapper;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Commits;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.ICommitsView;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IView;

public class CommitsListPresenter extends BasePresenter {

    private static final String BUNDLE_COMMITS_LIST_KEY = "BUNDLE_COMMITS_LIST_KEY";
    private static final int COUNT_SUBSCRIPTION = 1;

    @Inject
    protected CommitsListMapper commitsListMapper;

    private int countCompletedSubscription = 0;

    private ICommitsView view;

    private List<Commits> commitsList;

    private Repository repository;

    // for DI
    @Inject
    public CommitsListPresenter() {
    }

    public void loadData() {
        String owner = repository.getOwnerName();
        String name = repository.getRepoName();

        showLoadingState();
        Subscription subscription = model.getCommitsList(owner, name)
                .map(commitsListMapper)
                .subscribe(new Observer<List<Commits>>() {

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
                    public void onNext(List<Commits> list) {
                        commitsList = list;
                        view.showCommits(list);
                    }
                });
        addSubscription(subscription);
    }

    public void onCreate(ICommitsView view, Repository repository) {
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
            commitsList = (List<Commits>) savedInstanceState.getSerializable(BUNDLE_COMMITS_LIST_KEY);
        }

        if (commitsList == null) {
            loadData();
        } else {
            view.showCommits(commitsList);
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        if (commitsList != null)
            outState.putSerializable(BUNDLE_COMMITS_LIST_KEY, new ArrayList<>(commitsList));
    }

    @Override
    protected IView getView() {
        return view;
    }

}
