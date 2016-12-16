package ua.pavlo.tikhonov.simplegithubclient.view.adapters;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Branch;

public class BranchesAdapter extends BaseAdapter<Branch> {

    public BranchesAdapter(List<Branch> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        String text = list.get(position).getName();
        holder.text.setText(text);
    }


}
