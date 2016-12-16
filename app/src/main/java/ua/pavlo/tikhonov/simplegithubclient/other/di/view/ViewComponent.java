package ua.pavlo.tikhonov.simplegithubclient.other.di.view;

import javax.inject.Singleton;

import dagger.Component;
import ua.pavlo.tikhonov.simplegithubclient.view.fragments.RepoListFragment;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(RepoListFragment repoListFragment);
}
