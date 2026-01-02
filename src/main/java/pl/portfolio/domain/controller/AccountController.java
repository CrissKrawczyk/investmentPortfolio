package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.portfolio.domain.controller.dto.account.AccountResponse;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.service.AccountService;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.createAccount(account);
        return new ResponseEntity<>(AccountResponse.fromAccount(savedAccount), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Integer id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(AccountResponse.fromAccount(account), HttpStatus.OK);
    }

}
