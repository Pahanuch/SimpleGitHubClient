package ua.pavlo.tikhonov.simplegithubclient.presenter;

import android.app.Activity;
import android.text.TextUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import ua.pavlo.tikhonov.simplegithubclient.model.api.basic.AccessToken;
import ua.pavlo.tikhonov.simplegithubclient.model.api.basic.Auth;
import ua.pavlo.tikhonov.simplegithubclient.presenter.mappers.UserMapper;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.ILoginView;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IView;

public class LoginPresenter extends BasePresenter {

    @Inject
    protected UserMapper userMapper;

    private Activity context;

    private ILoginView view;

    // for DI
    @Inject
    public LoginPresenter() {
    }

    @Override
    protected IView getView() {
        return view;
    }

    public void onLoginButtonClick(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) return;

        AccessToken.setAccessToken(username, password);

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
                    public void onNext(User user) {
                        view.startProfileFragment();
                        Auth.saveToken(context, AccessToken.getAccessToken());
                    }
                });

        addSubscription(subscriptionUser);
    }

    public void onCreate(Activity context, ILoginView view) {
        App.getComponent().inject(this);
        this.context = context;
        this.view = view;
    }
}
