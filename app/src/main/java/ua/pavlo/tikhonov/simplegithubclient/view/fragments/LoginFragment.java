package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.*;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.pavlo.tikhonov.simplegithubclient.R;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.BasePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.LoginPresenter;
import ua.pavlo.tikhonov.simplegithubclient.view.ActivityCallback;

/**
 * Created by Tikho on 08.12.2016.
 */

public class LoginFragment extends BaseFragment implements ILoginView {

    @Bind(R.id.login)
    protected EditText etLogin;
    @Bind(R.id.password)
    protected EditText etPassword;

    @OnClick(R.id.sign_in_button)
    public void onClickSearch(View v) {
        if (presenter != null) {
            presenter.onLoginButtonClick(etLogin.getText().toString(), etPassword.getText().toString());
        }
    }

    @Inject
    protected LoginPresenter presenter;

    private ActivityCallback activityCallback;

    public static LoginFragment newInstance() {
        LoginFragment myFragment = new LoginFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        presenter.onCreate(getActivity(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        activityCallback.setTitle(getResources().getString(R.string.title_login), false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

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
    public void startProfileFragment() {
        activityCallback.startProfileFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}

