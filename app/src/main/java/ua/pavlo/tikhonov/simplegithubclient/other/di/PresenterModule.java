package ua.pavlo.tikhonov.simplegithubclient.other.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;
import ua.pavlo.tikhonov.simplegithubclient.model.Model;
import ua.pavlo.tikhonov.simplegithubclient.model.ModelImpl;

@Module

public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
