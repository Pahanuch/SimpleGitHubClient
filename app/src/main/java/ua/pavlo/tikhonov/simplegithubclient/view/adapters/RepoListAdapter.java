package ua.pavlo.tikhonov.simplegithubclient.view.adapters;

import android.content.res.Resources;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoListPresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;

public class RepoListAdapter extends BaseRepoListAdapter<Repository> {

    private Resources res = App.getContext().getResources();

    private RepoListPresenter presenter;

    public RepoListAdapter(List<Repository> list, RepoListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseRepoListAdapter.ViewHolder viewHolder, int i) {

        if (i % 2 == 0) viewHolder.itemView.setBackgroundColor(res.getColor(R.color.lightGrey));
        else viewHolder.itemView.setBackgroundColor(res.getColor(R.color.white));

        Repository repo = list.get(i);
        viewHolder.title.setText(repo.getRepoName());
        viewHolder.description.setText(repo.getDescription());
        viewHolder.forks.setText(res.getString(R.string.forks) + String.valueOf(repo.getForksCount()));
        viewHolder.watches.setText(res.getString(R.string.watches) + String.valueOf(repo.getWatchesCount()));

        viewHolder.btnRepoInfo.setOnClickListener(v ->
                presenter.clickRepo(repo));

        viewHolder.btnCommits.setOnClickListener(v ->
                presenter.clickCommit(repo));
    }

    public void setRepoList(List<Repository> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
