package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAllByPortfolio(Portfolio portfolio);
    List<Account> findAllByGroup(AccountGroup accountGroup);

}
