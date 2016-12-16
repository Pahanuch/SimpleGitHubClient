package ua.pavlo.tikhonov.simplegithubclient.presenter.mappers;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import ua.pavlo.tikhonov.simplegithubclient.model.dto.UserDTO;
import ua.pavlo.tikhonov.simplegithubclient.presenter.vo.User;

public class UserMapper implements Func1<UserDTO, User> {

    @Inject
    public UserMapper() {
    }

    @Override
    public User call(UserDTO userDTOs) {
        if (userDTOs == null) {
            return null;
        }
        User user = Observable.from(new UserDTO[]{userDTOs})
                .map(userDTO -> new User(userDTO.getLogin(), userDTO.getAvatarUrl(), userDTO.getName(),
                        userDTO.getEmail()))
                .toBlocking()
                .first();
        return user;
    }
}
