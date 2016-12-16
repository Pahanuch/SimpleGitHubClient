package ua.pavlo.tikhonov.simplegithubclient.other.di;

import javax.inject.Singleton;

import dagger.Component;
import ua.pavlo.tikhonov.simplegithubclient.model.ModelImpl;
import ua.pavlo.tikhonov.simplegithubclient.presenter.BasePresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.CommitsListPresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoInfoPresenter;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoListPresenter;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.CommitsFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.LoginFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.ProfileFragment;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.RepoInfoFragment;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(RepoListPresenter repoListPresenter);

    void inject(RepoInfoPresenter repoInfoPresenter);

    void inject(CommitsListPresenter commitsListPresenter);

    void inject(LoginFragment loginFragment);

    void inject(ProfileFragment profileFragment);

    void inject(RepoInfoFragment repoInfoFragment);

    void inject(CommitsFragment commitsFragment);

}
