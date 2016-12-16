package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.model.api.basic.Auth;
import ua.pavlo.tikhonov.simplegithubclient.presenter.BasePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.ProfilePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;

/**
 * Created by Tikho on 08.12.2016.
 */

public class ProfileFragment extends BaseFragment implements IProfileView {

    @Bind(R.id.tv_login)
    protected TextView tvLogin;

    @Bind(R.id.tv_name)
    protected TextView tvName;

    @Bind(R.id.tv_email)
    protected TextView tvEmail;

    @Bind(R.id.iv_avatar)
    protected ImageView ivAvatar;

    @Bind(R.id.button_start_repo_list)
    protected Button buttonStartRepoList;

    @Bind(R.id.button_exit)
    protected Button buttonExit;

    @Inject
    protected ProfilePresenter presenter;

    @OnClick(R.id.button_start_repo_list)
    public void onClickSearch(View v) {
        if (presenter != null) {
            presenter.onStartRepoList(presenter.getUser());
        }
    }

    @OnClick(R.id.button_exit)
    public void onClickExit(View v) {
        if (presenter != null) {
            Auth.clearToken(getActivity());
            presenter.onStartLogin();
        }
    }

    public static ProfileFragment newInstance() {
        ProfileFragment myFragment = new ProfileFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        presenter.onCreate(this);
        presenter.loadUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        activityCallback.setTitle(getResources().getString(R.string.title_profile), false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        presenter.onCreateView(savedInstanceState);

        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(getView().findViewById(R.id.sign_in_button), text, Snackbar.LENGTH_LONG).show();
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
    public void startRepoListFragment(User user) {
        activityCallback.startRepoListFragment(user);
    }

    @Override
    public void showUserInfo(User user) {
        tvLogin.setText(user.getLogin());
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());

        Picasso.with(getContext())
                .load(user.getAvatarUrl())
                .into(ivAvatar);
    }

    @Override
    public void onStartLogin() {
        activityCallback.startLoginFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}

