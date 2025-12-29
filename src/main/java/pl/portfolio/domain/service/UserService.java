package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
