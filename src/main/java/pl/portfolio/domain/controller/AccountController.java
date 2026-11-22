package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.controller.dto.account.AccountResponse;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.PortfolioService;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<ValidatedObject<AccountResponse>> createAccount(@RequestBody Account account, @RequestParam Integer portfolioId) {
        Portfolio portfolio = portfolioService.getPortfolio(portfolioId);
        account.setPortfolio(portfolio);
        ValidatedObject<Account> savedAccount = accountService.createAccount(account);
        AccountResponse accountResponse = AccountResponse.fromAccount(savedAccount.getObject());
        return new ResponseEntity<>(savedAccount.forNewObject(accountResponse), savedAccount.getStatusByErrors(HttpStatus.CREATED));
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Integer id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(AccountResponse.fromAccount(account), HttpStatus.OK);
    }

}
