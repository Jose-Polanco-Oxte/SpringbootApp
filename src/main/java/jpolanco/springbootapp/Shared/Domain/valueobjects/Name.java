package jpolanco.springbootapp.Shared.Domain.valueobjects;

import jpolanco.springbootapp.Shared.Domain.patterns.Result;

public class Name {
    private final String firstName;
    private final String lastName;

    private Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Result<Name> create(String firstName, String lastName) {
        return validate(firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private static Result<Name> validate(String... values) {
        String[] fields = {"First Name", "Last Name"};
        int counter = 0;
        for (String value : values) {
            if (value == null || value.isBlank()) {
                return Result.failure(fields[counter] + " value is empty", fields[counter] + " cannot be empty");
            }
            if (value.length() > 50) {
                return Result.failure(fields[counter] + " value is too long", fields[counter] + " cannot be longer than 50 characters");
            }
            if (value.length() < 2) {
                return Result.failure(fields[counter] + " value is too short", fields[counter] + " cannot be shorter than 2 characters");
            }
            if (value.contains(" ")) {
                return Result.failure(fields[counter] + " contains spaces", fields[counter] + " cannot contain spaces");
            }
            if (value.contains(".")) {
                return Result.failure(fields[counter] + " contains dots", fields[counter] + " cannot contain dots");
            }
            if (value.chars().anyMatch(c -> c > 127)) {
                return Result.failure(fields[counter] + " contains unicode characters", fields[counter] + " cannot contain unicode characters");
            }
            counter++;
        }
        return Result.success(new Name(values[0], values[1]));
    }
}