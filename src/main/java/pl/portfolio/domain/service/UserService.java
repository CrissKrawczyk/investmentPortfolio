package pl.portfolio.domain.service;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortfolioService portfolioService;

    public User createUser(User user) {
        User createdUser = userRepository.save(user);
        portfolioService.createMainPortfolio(user);
        return createdUser;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByLogin(userDetails.getUsername());
    }
}
