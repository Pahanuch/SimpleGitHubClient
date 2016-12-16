package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.di.view.DaggerViewComponent;
import ua.pavlo.tikhonov.simplegithubclient.other.di.view.ViewComponent;
import ua.pavlo.tikhonov.simplegithubclient.other.di.view.ViewDynamicModule;
import ua.pavlo.tikhonov.simplegithubclient.presenter.BasePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoListPresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;
import ua.pavlo.tikhonov.simplegithubclient.view.adapters.RepoListAdapter;

public class RepoListFragment extends BaseFragment implements IRepoListView {

    private static final String BUNDLE_USER_KEY = "BUNDLE_USER_KEY";

    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Inject
    protected RepoListPresenter presenter;

    private RepoListAdapter adapter;

    private ViewComponent viewComponent;

    public static RepoListFragment newInstance(User user) {
        RepoListFragment myFragment = new RepoListFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_USER_KEY, user);
        myFragment.setArguments(args);

        return myFragment;
    }

    private User getUserVO() {
        return (User) getArguments().getSerializable(BUNDLE_USER_KEY);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        activityCallback.setTitle(getResources().getString(R.string.title_repo_list), true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepoListAdapter(new ArrayList<>(), presenter);
        recyclerView.setAdapter(adapter);

        presenter.onCreateView(savedInstanceState);
        presenter.onLoadData(getUserVO().getLogin());

        return view;
    }


    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showRepoList(List<Repository> repoList) {
        adapter.setRepoList(repoList);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    public String getLogin() {
        return getUserVO().getLogin();
    }

    @Override
    public void startRepoInfoFragment(Repository repository) {
        activityCallback.startRepoInfoFragment(repository);
    }

    @Override
    public void startCommitsFragment(Repository repository) {
        activityCallback.startCommitsFragment(repository);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}
