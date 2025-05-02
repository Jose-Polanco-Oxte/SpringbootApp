package jpolanco.springbootapp.user.application.uc;

import jpolanco.springbootapp.shared.domain.Result;
import jpolanco.springbootapp.user.domain.model.User;

public interface GetUserByIdUC {
    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a Result object containing the User if found, or an error message if not found
     */
    Result<User> get(String userId);
}
