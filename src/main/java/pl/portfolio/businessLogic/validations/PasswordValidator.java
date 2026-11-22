package pl.portfolio.businessLogic.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {
    private final List<String> errors = new ArrayList<>();
    private final String password;

    public PasswordValidator(String password) {
        this.password = password;
    }

    public List<String> validatePassword() {
        validateLength();
        validateSpecialCharacter();
        validateNumber();
        validateUppercaseLetter();
        return errors;
    }

    private void validateLength() {
        if (password.length() < 14) {
            errors.add("tooShort");
        }
    }

    private void validateSpecialCharacter() {
        boolean hasSpecialCharacter = Pattern.compile("[^0-9a-zA-Z]+")
                .matcher(password)
                .find();
        if (!hasSpecialCharacter) {
            errors.add("noSpecialCharacter");
        }
    }

    private void validateNumber() {
        boolean hasNumber = Pattern.compile("[0-9]+")
                .matcher(password)
                .find();
        if (!hasNumber) {
            errors.add("noNumber");
        }
    }

    private void validateUppercaseLetter() {
        boolean hasUppercaseLetter = Pattern.compile("[A-Z]+")
                .matcher(password)
                .find();
        if (!hasUppercaseLetter) {
            errors.add("noUppercaseLetter");
        }
    }

}
