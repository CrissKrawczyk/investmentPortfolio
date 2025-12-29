package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

    public Portfolio findByOwner(User user);
}
