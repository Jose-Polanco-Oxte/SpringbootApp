package jpolanco.springbootapp.user.application.ports.input;

import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.user.domain.model.User;

import java.util.List;

public interface UserServicePort {

    Result<String> create(RegisterCommand command);

    Result<String> update(UserCommand command);

    Result<String> deleteById(String userId);

    Result<String> getById(String userId);

    Result<List<User>> getAll();

    record RegisterCommand(String firstName, String lastName, String email, String password){}
    record UserCommand(String firstName, String lastName, String email, String password, String userId){}
}