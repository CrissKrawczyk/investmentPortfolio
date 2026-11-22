package pl.portfolio.domain.service;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.UserValidations;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ValidatedObject<User> createUser(User user) {
        String password = user.getPassword();
        UserValidations userValidations = new UserValidations(user, userRepository);
        Map<String, String> errors = userValidations.getUserErrors();
        if (!errors.isEmpty()) {
            return new ValidatedObject<>(user, errors);
        }

        user.setPassword(passwordEncoder.encode(password));
        User createdUser = userRepository.save(user);
        portfolioService.createMainPortfolio(user);
        return new ValidatedObject<>(createdUser);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByLogin(userDetails.getUsername());
    }
}
