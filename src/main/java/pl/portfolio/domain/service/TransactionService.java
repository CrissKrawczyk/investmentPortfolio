package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Transaction;
import pl.portfolio.domain.repository.TransactionRepository;

import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ValidatedObject<Transaction> createTransaction(Transaction transaction) {
        if (transaction.getDate() == null) {
            transaction.setDate(new Date());
        }
        return new ValidatedObject<>(transactionRepository.save(transaction));
    }

}
