package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.BasePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.CommitsListPresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Commits;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.view.adapters.CommitsListAdapter;

public class CommitsFragment extends BaseFragment implements ICommitsView {

    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Inject
    protected CommitsListPresenter presenter;

    public static CommitsFragment newInstance(Repository repository) {
        CommitsFragment myFragment = new CommitsFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, repository);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    private Repository getRepositoryVO() {
        return (Repository) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        presenter.onCreate(this, getRepositoryVO());
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        activityCallback.setTitle(getResources().getString(R.string.title_commits), true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.onCreateView(savedInstanceState);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showCommits(List<Commits> commitsList) {
        recyclerView.setAdapter(new CommitsListAdapter(commitsList));
    }

}
