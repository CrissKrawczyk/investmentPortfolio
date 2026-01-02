package pl.portfolio.domain.controller.dto.Transaction;

import pl.portfolio.domain.model.Transaction;

public record TransactionResponse(Integer id, double amount, double price) {

    static public TransactionResponse fromTransaction(Transaction transaction) {
        return new TransactionResponse(transaction.getId(), transaction.getAmount(), transaction.getPrice());
    }

}
