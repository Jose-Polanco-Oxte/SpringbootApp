package jpolanco.springbootapp.user.application.uc;

import jpolanco.springbootapp.user.application.utils.UserUpdateBuilder;
import jpolanco.springbootapp.user.domain.model.User;

public interface UpdateUserUC {
    /**
     * Updates a user with the given ID and changes.
     *
     * @param user the user to be updated
     * @return a Result containing the builder for update user and can be use like an api
     */
    UserUpdateBuilder setChanges(User user);
}