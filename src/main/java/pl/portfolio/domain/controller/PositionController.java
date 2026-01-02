package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.domain.controller.dto.Transaction.TransactionResponse;
import pl.portfolio.domain.controller.dto.position.PositionResponse;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.Position;
import pl.portfolio.domain.model.Transaction;
import pl.portfolio.domain.model.TransactionType;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.PositionService;
import pl.portfolio.domain.service.TransactionService;

@Controller
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    AccountService accountService;

    @Autowired
    PositionService positionService;

    @Autowired
    TransactionService transactionService;

    @PostMapping
    @RequestMapping("/{accountId}/addPosition")
    public ResponseEntity<PositionResponse> createPosition(@RequestBody Position position, @PathVariable Integer accountId) {
        Account account = accountService.getAccount(accountId);
        position.setAccount(account);
        Position savedPosition = positionService.createPosition(position);
        return new ResponseEntity<>(PositionResponse.fromPosition(savedPosition), HttpStatus.CREATED);
    }

    @PostMapping
    @RequestMapping("/{positionId}/buy")
    public ResponseEntity<TransactionResponse> buy(@RequestBody Transaction transaction, @PathVariable Integer positionId) {
        Position position = positionService.getPosition(positionId);
        transaction.setPosition(position);
        transaction.setTransactionType(TransactionType.Purchase);
        Transaction savedTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(TransactionResponse.fromTransaction(savedTransaction), HttpStatus.CREATED);
    }

    @PostMapping
    @RequestMapping("/{positionId}/sell")
    public ResponseEntity<TransactionResponse> sell(@RequestBody Transaction transaction, @PathVariable Integer positionId) {
        Position position = positionService.getPosition(positionId);
        transaction.setPosition(position);
        transaction.setTransactionType(TransactionType.Sale);
        Transaction savedTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(TransactionResponse.fromTransaction(savedTransaction), HttpStatus.CREATED);
    }

}
