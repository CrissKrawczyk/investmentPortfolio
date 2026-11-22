package pl.portfolio.businessLogic.validations;

import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserValidations {

    private final UserRepository userRepository;
    private final User user;

    public UserValidations(User user, UserRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;
    }

    public Map<String, String> getUserErrors() {
        Map<String, String> errors = new HashMap<>();
        List<String> passwordErrors = validatePassword(user.getPassword());
        if (!passwordErrors.isEmpty()) {
            errors.put("password", String.join("|", passwordErrors));
        }
        if (loginExists(user.getLogin())) {
            errors.put("login", "notUnique");
        }
        return errors;
    }

    private List<String> validatePassword(String password) {
        return new PasswordValidator(password).validatePassword();
    }

    private boolean loginExists(String login) {
        User other = userRepository.findByLogin(login);
        return other != null;
    }

}
