package jpolanco.springbootapp.User.Application;

import jpolanco.springbootapp.Shared.Application.MappeableCommand;
import jpolanco.springbootapp.Shared.Domain.patterns.Result;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Email;
import jpolanco.springbootapp.Shared.Domain.valueobjects.Name;
import jpolanco.springbootapp.Shared.Domain.valueobjects.QR;
import jpolanco.springbootapp.User.Domain.Role;
import jpolanco.springbootapp.User.Domain.User;

public record RegisterCommand(String email, String firstname, String lastName, String password, String qr) implements MappeableCommand<Result<User>> {
    @Override
    public Result<User> toDomain() {
        Result<Email> emailResult = Email.create(email);
        Result<Name> nameResult = Name.create(firstname, lastName);
        Result<QR> qrResult = QR.create(qr);

        if (!emailResult.isSuccess()) {
            return Result.failure(emailResult.getError(), emailResult.getUserFriendlyMessage());
        }
        if (!nameResult.isSuccess()) {
            return Result.failure(nameResult.getError(), nameResult.getUserFriendlyMessage());
        }
        if (!qrResult.isSuccess()) {
            return Result.failure(qrResult.getError(), qrResult.getUserFriendlyMessage());
        }

        User user = new User();
        user.setEmail(emailResult.getValue());
        user.setName(nameResult.getValue());
        user.setPassword(password);
        user.setQr(qrResult.getValue());
        user.getRoles().add(new Role(1L,"USER"));
        return Result.success(user);
    }
}
