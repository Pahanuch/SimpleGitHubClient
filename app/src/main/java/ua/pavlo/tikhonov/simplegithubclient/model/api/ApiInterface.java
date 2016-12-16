package ua.pavlo.tikhonov.simplegithubclient.model.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.BranchDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.CommitDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.ContributorDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.RepositoryDTO;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.UserDTO;

public interface ApiInterface {

    @GET("/user")
    Observable<UserDTO> basicLogin();

    @GET("/users/{user}/repos")
    Observable<List<RepositoryDTO>> getRepositories(@Path("user") String user);

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<ContributorDTO>> getContributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/branches")
    Observable<List<BranchDTO>> getBranches(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/commits")
    Observable<List<CommitDTO>> getCommits(@Path("owner") String owner, @Path("repo") String repo);
}
