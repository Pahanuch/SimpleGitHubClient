package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

import ua.pavlo.tikhonov.simplegithubclient.presenter.IPresenter;
import ua.pavlo.tikhonov.simplegithubclient.view.ActivityCallback;

public abstract class BaseFragment extends Fragment implements IView {

    protected ActivityCallback activityCallback;

    protected abstract IPresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
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
    public void showLoading() {
        activityCallback.showProgressBar();
    }

    @Override
    public void hideLoading() {
        activityCallback.hideProgressBar();
    }
}

