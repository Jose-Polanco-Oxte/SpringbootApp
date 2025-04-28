package jpolanco.springbootapp.user.domain.model.valueobjects;

import jpolanco.springbootapp.shared.application.Error;
import jpolanco.springbootapp.shared.application.Result;
import jpolanco.springbootapp.shared.application.ResultBuilder;
import jpolanco.springbootapp.user.domain.errors.NameError;

public class FullName {
    private String firstName;
    private String lastName;

    private FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Result<FullName> create(String firstName, String lastName) {
        return ResultBuilder
                .from(ensureValueIsValid(firstName))
                .then(validFirst -> ensureValueIsValid(lastName))
                .then(validLast -> Result.success(new FullName(firstName, validLast)))
                .result();
    }

    private static Result<String> ensureValueIsValid(String value) {
        if (value == null || value.isEmpty()) {
            return Result.failure(Error.NULLVALUE);
        }
        if (value.length() < 2) {
            return Result.failure(NameError.TOOSHORT);
        }
        if (value.matches(".*\\d.*")) {
            return Result.failure(NameError.INVALIDCHARACTERS);
        }
        if (value.length() > 50) {
            return Result.failure(NameError.TOOLONG);
        }
        return Result.success(value);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
