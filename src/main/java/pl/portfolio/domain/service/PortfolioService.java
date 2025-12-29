package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.PortfolioRepository;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Lazy
    @Autowired
    private UserService userService;

    public Portfolio createMainPortfolio(User user) {
        Portfolio portfolio = new Portfolio();
        portfolio.setOwner(user);
        portfolio.setName("mainUserPortfolio");
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    public Portfolio getCurrentUserPortfolio() {
        User currentUser = userService.getCurrentUser();
        return portfolioRepository.findByOwner(currentUser);
    }

}
