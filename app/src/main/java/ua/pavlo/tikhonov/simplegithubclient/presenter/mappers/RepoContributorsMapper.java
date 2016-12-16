package ua.pavlo.tikhonov.simplegithubclient.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.ContributorDTO;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.Contributor;

public class RepoContributorsMapper implements Func1<List<ContributorDTO>, List<Contributor>> {

    @Inject
    public RepoContributorsMapper() {
    }

    @Override
    public List<Contributor> call(List<ContributorDTO> contributorDTOs) {
        if (contributorDTOs == null) {
            return null;
        }
        List<Contributor> contributors = Observable.from(contributorDTOs)
                .map(contributorDTO -> new Contributor(contributorDTO.getLogin()))
                .toList()
                .toBlocking()
                .first();
        return contributors;
    }
}
