package ua.pavlo.tikhonov.simplegithubclient.other.di.view;

import dagger.Module;
import dagger.Provides;
import ua.pavlo.tikhonov.simplegithubclient.presenter.RepoListPresenter;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.IRepoListView;

@Module
public class ViewDynamicModule {

    private IRepoListView view;
    public ViewDynamicModule(IRepoListView view) {
        this.view = view;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view);
    }

}
