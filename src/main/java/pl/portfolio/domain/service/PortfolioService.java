package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.PortfolioRepository;

import java.util.List;

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
        portfolio.setTestPortfolio(false);
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    public ValidatedObject<Portfolio> createTestPortfolio(Portfolio portfolio) {
        User user = userService.getCurrentUser();
        portfolio.setOwner(user);
        portfolio.setTestPortfolio(true);
        portfolioRepository.save(portfolio);
        return new ValidatedObject<>(portfolio);
    }

    public void deleteTestPortfolio(Integer id) {
        User currentUser = userService.getCurrentUser();
        Portfolio portfolio = getPortfolio(id);
        Integer portfolioUserId = portfolio.getOwner().getId();
        if (portfolio.getTestPortfolio() && portfolioUserId.equals(currentUser.getId())) {
            portfolioRepository.delete(portfolio);
        } else {
            throw new RuntimeException("Unauthorized profile deletion");
        }
    }

    public Portfolio getPortfolio(Integer id) {
        return portfolioRepository.getReferenceById(id);
    }

    public Portfolio getCurrentUserPortfolio() {
        User currentUser = userService.getCurrentUser();
        return getUserPortfolio(currentUser);
    }

    public Portfolio getUserPortfolio(User user) {
        return portfolioRepository.findByOwnerAndTestPortfolio(user, false);
    }

    public List<Portfolio> getCurrentUserPortfolios() {
        User currentUser = userService.getCurrentUser();
        return portfolioRepository.findAllByOwner(currentUser);
    }

}
