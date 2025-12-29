package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.PortfolioRepository;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio createMainPortfolio(User user) {
        Portfolio portfolio = new Portfolio();
        portfolio.setOwner(user);
        portfolio.setName("mainUserPortfolio");
        portfolioRepository.save(portfolio);
        return portfolio;
    }
}
