package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.BasePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoInfoPresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Branch;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Contributor;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;
import ua.pavlo.tikhonov.simplegithubclient.view.adapters.BranchesAdapter;
import ua.pavlo.tikhonov.simplegithubclient.view.adapters.ContributorsAdapter;

public class RepoInfoFragment extends BaseFragment implements IRepoInfoView {

    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Bind(R.id.repo_login)
    protected TextView tvLogin;

    @Bind(R.id.repo_forks)
    protected TextView tvForks;

    @Bind(R.id.repo_watches)
    protected TextView tvWatches;

    @Bind(R.id.repo_description)
    protected TextView tvDescription;

    @Bind(R.id.iv_avatar)
    protected ImageView ivAvatar;

    @Bind(R.id.recycler_view_branches)
    protected RecyclerView branchesRecyclerView;

    @Bind(R.id.recycler_view_contributors)
    protected RecyclerView contributorsRecyclerView;

    @Bind(R.id.linear_layout)
    protected View layout;

    @Inject
    protected RepoInfoPresenter presenter;

    public static RepoInfoFragment newInstance(Repository repository) {
        RepoInfoFragment myFragment = new RepoInfoFragment();

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
        activityCallback.setTitle(getResources().getString(R.string.title_info), true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_info, container, false);
        ButterKnife.bind(this, view);

        String login = getRepositoryVO().getRepoName() + " (" + getRepositoryVO().getOwnerName() + ")";
        String forks = getResources().getString(R.string.forks) + String.valueOf(getRepositoryVO().getForksCount());
        String watches = getResources().getString(R.string.watches) + String.valueOf(getRepositoryVO().getWatchesCount());
        String description = getRepositoryVO().getDescription();

        tvLogin.setText(login);
        tvForks.setText(forks);
        tvWatches.setText(watches);
        tvDescription.setText(description);

        Picasso.with(getContext())
                .load(getRepositoryVO().getAvatar())
                .into(ivAvatar);

        branchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contributorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showContributors(List<Contributor> contributors) {
        contributorsRecyclerView.setAdapter(new ContributorsAdapter(contributors));
    }

    @Override
    public void showBranches(List<Branch> branches) {
        branchesRecyclerView.setAdapter(new BranchesAdapter(branches));
    }

}
