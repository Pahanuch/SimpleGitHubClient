package ua.pavlo.tikhonov.simplegithubclient.view.fragments;

public interface IView {

    void showError(String error);

    void showLoading();

    void hideLoading();
}
