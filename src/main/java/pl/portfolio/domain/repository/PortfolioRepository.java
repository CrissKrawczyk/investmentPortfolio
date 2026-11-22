package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    public List<Portfolio> findAllByOwner(User user);

    public Portfolio findByOwnerAndTestPortfolio(User user, Boolean testPortfolio);
}
