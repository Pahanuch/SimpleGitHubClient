package ua.pavlo.tikhonov.simplegithubclient.presenter;

import android.os.Bundle;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.presenter.mappers.UserMapper;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IProfileView;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IView;

public class ProfilePresenter extends BasePresenter {

    private static final String BUNDLE_PROFILE_KEY = "BUNDLE_PROFILE_KEY";

    @Inject
    protected UserMapper userMapper;

    private IProfileView view;

    private User user;

    // for DI
    @Inject
    public ProfilePresenter() {
    }

    @Override
    protected IView getView() {
        return view;
    }

    public void onStartRepoList(User user) {
       view.startRepoListFragment(user);
    }

    public void onStartLogin() {
        view.onStartLogin();
    }

    public void loadUser() {

        showLoadingState();

        Subscription subscriptionUser = model.getUser()
                .map(userMapper)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        hideLoadingState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingState();
                        showError(e);
                    }

                    @Override
                    public void onNext(User userData) {
                        user = userData;
                        view.showUserInfo(userData);
                    }
                });

        addSubscription(subscriptionUser);
    }

    public void onCreate(IProfileView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            user = (User) savedInstanceState.getSerializable(BUNDLE_PROFILE_KEY);
        }

        if (user == null) {
            loadUser();
        } else {
            view.showUserInfo(user);
        }
    }


    public void onSaveInstanceState(Bundle outState) {
        if (user != null) {
            outState.putSerializable(BUNDLE_PROFILE_KEY, user);
        }
    }

    public User getUser(){
        return user;
    }

}
