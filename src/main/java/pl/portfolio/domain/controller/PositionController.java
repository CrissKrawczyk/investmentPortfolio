package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.controller.dto.Transaction.TransactionResponse;
import pl.portfolio.domain.controller.dto.position.PositionRequest;
import pl.portfolio.domain.controller.dto.position.PositionResponse;
import pl.portfolio.domain.model.Position;
import pl.portfolio.domain.model.Transaction;
import pl.portfolio.domain.model.TransactionType;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.CurrencyService;
import pl.portfolio.domain.service.PositionService;
import pl.portfolio.domain.service.TransactionService;

@Controller
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    AccountService accountService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    PositionService positionService;

    @Autowired
    TransactionService transactionService;

    @PostMapping
    @RequestMapping("/{accountId}/addPosition")
    public ResponseEntity<ValidatedObject<PositionResponse>> createPosition(@RequestBody PositionRequest position, @PathVariable Integer accountId) {
        position.setAccountId(accountId);
        ValidatedObject<Position> savedPosition = positionService.createPosition(position.toPosition(currencyService, accountService));
        PositionResponse positionResponse = PositionResponse.fromPosition(savedPosition.getObject());
        return new ResponseEntity<>(savedPosition.forNewObject(positionResponse), savedPosition.getStatusByErrors(HttpStatus.CREATED));
    }

    @PostMapping
    @RequestMapping("/{positionId}/buy")
    public ResponseEntity<ValidatedObject<TransactionResponse>> buy(@RequestBody Transaction transaction, @PathVariable Integer positionId) {
        Position position = positionService.getPosition(positionId);
        transaction.setPosition(position);
        transaction.setTransactionType(TransactionType.Purchase);
        ValidatedObject<Transaction> savedTransaction = transactionService.createTransaction(transaction);
        TransactionResponse transactionResponse = TransactionResponse.fromTransaction(savedTransaction.getObject());
        return new ResponseEntity<>(savedTransaction.forNewObject(transactionResponse), savedTransaction.getStatusByErrors(HttpStatus.CREATED));
    }

    @PostMapping
    @RequestMapping("/{positionId}/sell")
    public ResponseEntity<ValidatedObject<TransactionResponse>> sell(@RequestBody Transaction transaction, @PathVariable Integer positionId) {
        Position position = positionService.getPosition(positionId);
        transaction.setPosition(position);
        transaction.setTransactionType(TransactionType.Sale);
        ValidatedObject<Transaction> savedTransaction = transactionService.createTransaction(transaction);
        TransactionResponse transactionResponse = TransactionResponse.fromTransaction(savedTransaction.getObject());
        return new ResponseEntity<>(savedTransaction.forNewObject(transactionResponse), savedTransaction.getStatusByErrors(HttpStatus.CREATED));
    }

}
