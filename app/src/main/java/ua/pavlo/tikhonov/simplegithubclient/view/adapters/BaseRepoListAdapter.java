package ua.pavlo.tikhonov.simplegithubclient.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.R;

public abstract class BaseRepoListAdapter<T> extends RecyclerView.Adapter<BaseRepoListAdapter.ViewHolder> {

    protected List<T> list;

    public BaseRepoListAdapter(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView forks;
        TextView watches;
        Button btnRepoInfo;
        Button btnCommits;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            forks = (TextView) itemView.findViewById(R.id.tv_forks);
            watches = (TextView) itemView.findViewById(R.id.tv_watches);
            btnRepoInfo = (Button) itemView.findViewById(R.id.btn_repo_info);
            btnCommits = (Button) itemView.findViewById(R.id.btn_commits);
        }
    }

}
