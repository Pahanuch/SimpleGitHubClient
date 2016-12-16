package ua.pavlo.tikhonov.simplegithubclient.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.mappers.RepoListMapper;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IRepoListView;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IView;

public class RepoListPresenter extends BasePresenter {

    private static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";

    @Inject
    protected RepoListMapper repoListMapper;

    private IRepoListView view;

    private List<Repository> repoList;

    // for DI
    @Inject
    public RepoListPresenter() {
    }

    public RepoListPresenter(IRepoListView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    protected IView getView() {
        return view;
    }

    public void onLoadData(String name) {
        if (TextUtils.isEmpty(name)) return;

        showLoadingState();
        Subscription subscription = model.getRepoList(name)
                .map(repoListMapper)
                .subscribe(new Observer<List<Repository>>() {

                    @Override
                    public void onCompleted() {
                        hideLoadingState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingState();
                        showError(e);
                    }

                    @Override
                    public void onNext(List<Repository> list) {
                        if (list != null && !list.isEmpty()) {
                            repoList = list;
                            view.showRepoList(list);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            repoList = (List<Repository>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        }
        if (isRepoListNotEmpty()) {
            view.showRepoList(repoList);
        }
    }

    private boolean isRepoListNotEmpty() {
        return (repoList != null && !repoList.isEmpty());
    }

    public void onSaveInstanceState(Bundle outState) {
        if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
        }
    }

    public void clickRepo(Repository repository) {
        view.startRepoInfoFragment(repository);
    }

    public void clickCommit(Repository repository) {
        view.startCommitsFragment(repository);
    }

}
