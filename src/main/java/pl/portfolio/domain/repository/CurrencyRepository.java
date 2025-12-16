package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
