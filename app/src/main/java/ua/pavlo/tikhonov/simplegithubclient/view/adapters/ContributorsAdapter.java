package ua.pavlo.tikhonov.simplegithubclient.view.adapters;

import java.util.List;

import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Contributor;

public class ContributorsAdapter extends BaseAdapter<Contributor> {

    public ContributorsAdapter(List<Contributor> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        String text = list.get(i).getName();
        viewHolder.text.setText(text);
    }
}
