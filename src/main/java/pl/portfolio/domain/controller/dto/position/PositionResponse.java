package pl.portfolio.domain.controller.dto.position;

import pl.portfolio.domain.model.Position;
import pl.portfolio.domain.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public record PositionResponse(String name, Integer id, List<TransactionInPosition> transactions) {

    static public PositionResponse fromPosition(Position position) {
        List<Transaction> transactions = position.getTransactions();
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        List<TransactionInPosition> transactionsInPosition = transactions
                .stream()
                .map(t -> new TransactionInPosition(t.getAmount()))
                .toList();
        return new PositionResponse(position.getName(), position.getId(), transactionsInPosition);
    }

}
