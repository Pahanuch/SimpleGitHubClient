package ua.pavlo.tikhonov.simplegithubclient.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.CommitDTO;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Commits;

public class CommitsListMapper implements Func1<List<CommitDTO>, List<Commits>> {

    @Inject
    public CommitsListMapper() {
    }

    @Override
    public List<Commits> call(List<CommitDTO> commitsDTOs) {
        if (commitsDTOs == null) {
            return null;
        }
        List<Commits> commitsList = Observable.from(commitsDTOs)
                .map(commitsDTO -> new Commits(commitsDTO.getSha(),
                        commitsDTO.getCommit().getMessage(), commitsDTO.getCommit().getAuthor().getName(), commitsDTO.getCommit().getAuthor().getDate()))
                .toList()
                .toBlocking()
                .first();
        return commitsList;
    }

}
