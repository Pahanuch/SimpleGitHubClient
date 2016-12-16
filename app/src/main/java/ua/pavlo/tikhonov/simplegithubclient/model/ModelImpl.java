package ua.pavlo.tikhonov.simplegithubclient.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import ua.pavlo.tikhonov.simplegithubclient.model.api.ApiInterface;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.BranchDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.CommitDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.ContributorDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.RepositoryDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.UserDTO;
import ua.pavlo.tikhonov.simplegithubclient.other.App;
import ua.pavlo.tikhonov.simplegithubclient.other.Const;

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread); // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
    }

    @Override
    public Observable<UserDTO> getUser() {
        return apiInterface
                .basicLogin()
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<RepositoryDTO>> getRepoList(String name) {
        return apiInterface
                .getRepositories(name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<BranchDTO>> getRepoBranches(String owner, String name) {
        return apiInterface
                .getBranches(owner, name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<ContributorDTO>> getRepoContributors(String owner, String name) {
        return apiInterface
                .getContributors(owner, name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<CommitDTO>> getCommitsList(String owner, String name) {
        return apiInterface
                .getCommits(owner, name)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
