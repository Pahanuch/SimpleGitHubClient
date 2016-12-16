package ua.pavlo.tikhonov.simplegithubclient.other.di;

import dagger.Module;
import dagger.Provides;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoInfoPresenter;

@Module
public class ViewModule {

    @Provides
    RepoInfoPresenter provideRepoInfoPresenter() {
        return new RepoInfoPresenter();
    }

}
