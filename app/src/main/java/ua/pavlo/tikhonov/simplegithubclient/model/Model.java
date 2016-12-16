package ua.pavlo.tikhonov.simplegithubclient.model;

import java.util.List;

import rx.Observable;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.BranchDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.CommitDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.ContributorDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.RepositoryDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.UserDTO;

public interface Model {

    Observable<UserDTO> getUser();

    Observable<List<RepositoryDTO>> getRepoList(String name);

    Observable<List<BranchDTO>> getRepoBranches(String owner, String name);

    Observable<List<ContributorDTO>> getRepoContributors(String owner, String name);

    Observable<List<CommitDTO>> getCommitsList(String owner, String name);
}
