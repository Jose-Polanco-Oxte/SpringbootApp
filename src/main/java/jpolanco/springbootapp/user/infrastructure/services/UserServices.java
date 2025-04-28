package jpolanco.springbootapp.user.infrastructure.services;

import jpolanco.springbootapp.user.application.ports.input.holders.UserHolder;
import jpolanco.springbootapp.user.application.services.uc.*;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.ResponseDto;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.UpdateRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServices {

    private final UpdateUserByIdUC updateUserById;
    private final GetUserByIdUC getUserById;
    private final GetAllUsersUC getAllUsers;
    private final DeleteUserByIdUC deleteUserById;
    private final GetUserByEmailUC getUserByEmail;

    public ResponseDto updateUser(UserUpdateDto request, String userId) {
        var result = updateUserById.execute(new UserHolder(
                userId,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
        ));
        if (result.isFailure()) {
            return ResponseDto.error(result.getMessage(), null);
        }
        return ResponseDto.ok("User updated successfully", null);
    }

    public ResponseDto getUser(String userId) {
        var result = getUserById.execute(userId);
        if (result.isFailure()) {
            return ResponseDto.error(result.getMessage(), null);
        }
        return ResponseDto.ok("User retrieved successfully", result.getValue());
    }

    public ResponseDto deleteUser(String userId) {
        var result = deleteUserById.execute(userId);
        if (result.isFailure()) {
            return ResponseDto.error(result.getMessage(), null);
        }
        return ResponseDto.ok("User deleted successfully", null);
    }

    public ResponseDto getAllUsers() {
        var result = getAllUsers.execute();
        if (result.isFailure()) {
            return ResponseDto.error(result.getMessage(), null);
        }
        return ResponseDto.ok("Users retrieved successfully", result.getValue());
    }

    public ResponseDto getUserByEmail(String email) {
        var result = getUserByEmail.execute(email);
        if (result.isFailure()) {
            return ResponseDto.error(result.getMessage(), null);
        }
        return ResponseDto.ok("User retrieved successfully", result.getValue());
    }
}
