package jpolanco.springbootapp.user.infrastructure.services.interfaces;

import jpolanco.springbootapp.shared.application.Dto;
import jpolanco.springbootapp.shared.domain.Result;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.UpdateEmailRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.UpdateNameRequest;
import jpolanco.springbootapp.user.infrastructure.adapters.input.dto.request.UpdatePasswordRequest;

public interface ProfileService {
    Result<Dto> get(String userId);
    Result<Dto> changeName(String userId, UpdateNameRequest dto);
    Result<Dto> changeEmail(String userId, UpdateEmailRequest dto);
    Result<Dto> deleteMe(String userId);
    Result<Dto> changePassword(String userId, UpdatePasswordRequest dto);
}
