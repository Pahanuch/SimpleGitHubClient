package ua.pavlo.tikhonov.simplegithubclient.presenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ua.pavlo.tikhonov.simplegithubclient.model.Model;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IView;

public abstract class BasePresenter implements IPresenter {

    @Inject
    protected Model model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    protected abstract IView getView();

    protected void showLoadingState() {
        getView().showLoading();
    }

    protected void hideLoadingState() {
        getView().hideLoading();
    }

    protected void showError(Throwable e) {
        getView().showError(e.getMessage());
    }

}
