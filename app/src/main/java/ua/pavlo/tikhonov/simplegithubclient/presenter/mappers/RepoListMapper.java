package ua.pavlo.tikhonov.simplegithubclient.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.RepositoryDTO;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Repository;

public class RepoListMapper implements Func1<List<RepositoryDTO>, List<Repository>> {

    @Inject
    public RepoListMapper() {
    }

    @Override
    public List<Repository> call(List<RepositoryDTO> repositoryDTOs) {
        if (repositoryDTOs == null) {
            return null;
        }
        List<Repository> repoList = Observable.from(repositoryDTOs)
                .map(repoDTO -> new Repository(repoDTO.getName(), repoDTO.getOwner().getLogin(), repoDTO.getOwner().getAvatarUrl(),
                        repoDTO.getDescription(), repoDTO.getForksCount(), repoDTO.getWatchersCount()))
                .toList()
                .toBlocking()
                .first();
        return repoList;
    }

}
