package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
