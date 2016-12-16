package ua.pavlo.tikhonov.simplegithubclient.view.adapters;

import android.content.res.Resources;
import android.graphics.Color;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Commits;

public class CommitsListAdapter extends BaseCommitsAdapter<Commits> {

    private Resources res = App.getContext().getResources();

    public CommitsListAdapter(List<Commits> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseCommitsAdapter.ViewHolder viewHolder, int i) {

        if (i % 2 == 0) viewHolder.itemView.setBackgroundColor(res.getColor(R.color.lightGrey));
        else viewHolder.itemView.setBackgroundColor(res.getColor(R.color.white));

        Commits commits = list.get(i);
        viewHolder.author.setText(commits.getAuthor());
        viewHolder.date.setText(commits.getDate());
        viewHolder.sha.setText(commits.getHash());
        viewHolder.message.setText(commits.getShortCommitMessage());
    }
}
