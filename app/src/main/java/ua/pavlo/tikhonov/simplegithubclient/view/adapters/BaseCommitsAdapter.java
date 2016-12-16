package ua.pavlo.tikhonov.simplegithubclient.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.R;

public abstract class BaseCommitsAdapter<T> extends RecyclerView.Adapter<BaseCommitsAdapter.ViewHolder> {

    protected List<T> list;

    public BaseCommitsAdapter(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.commits_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView sha;
        TextView message;
        TextView author;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            sha = (TextView) itemView.findViewById(R.id.tv_sha);
            message = (TextView) itemView.findViewById(R.id.tv_message);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

}
