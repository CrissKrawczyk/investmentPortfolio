package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;

import java.util.List;

public interface AccountGroupRepository extends JpaRepository<AccountGroup, Integer> {

    List<AccountGroup> findAllByPortfolio(Portfolio portfolio);

}
