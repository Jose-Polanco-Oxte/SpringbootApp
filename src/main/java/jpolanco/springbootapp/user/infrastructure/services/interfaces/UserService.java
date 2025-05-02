package jpolanco.springbootapp.user.infrastructure.services.interfaces;

import jpolanco.springbootapp.shared.application.Dto;
import jpolanco.springbootapp.shared.domain.Result;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.AllUserUpdateRequest;

import java.util.List;

public interface UserService {
    Result<Dto> getUserById(String userId);
    Result<Dto> getUserByEmail(String email);
    Result<List<Dto>> getAll();
    Result<List<Dto>> searchUsers(String searchTerm, int page, int size);
    Result<Dto> updateUser(AllUserUpdateRequest dto, String userId);
    Result<Dto> deleteUser(String userId);
}
