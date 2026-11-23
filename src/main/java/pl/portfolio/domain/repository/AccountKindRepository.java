package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.AccountKind;
import pl.portfolio.domain.model.Portfolio;

import java.util.List;

public interface AccountKindRepository extends JpaRepository<AccountKind, Integer> {

}
