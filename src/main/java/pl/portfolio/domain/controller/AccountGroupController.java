package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.portfolio.domain.controller.dto.account.AccountResponse;
import pl.portfolio.domain.controller.dto.group.GroupResponse;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.service.AccountGroupService;
import pl.portfolio.domain.service.AccountService;

@Controller
@RequestMapping("/api/accountGroup")
public class AccountGroupController {

    @Autowired
    AccountGroupService accountGroupService;

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody AccountGroup group) {
        AccountGroup savedGroup = accountGroupService.createAccountGroup(group);
        return new ResponseEntity<>(GroupResponse.forGroup(savedGroup), HttpStatus.CREATED);
    }

    @PostMapping
    @RequestMapping("/{id}/addAccount")
    public ResponseEntity<AccountResponse> addAccount(@RequestBody Account account, @PathVariable Integer id) {
        AccountGroup group = accountGroupService.getGroup(id);
        account.setGroup(group);
        Account savedAccount = accountService.createAccount(account);
        return new ResponseEntity<>(AccountResponse.fromAccount(savedAccount), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable Integer id) {
        AccountGroup group = accountGroupService.getGroup(id);
        return new ResponseEntity<>(GroupResponse.forGroup(group), HttpStatus.ACCEPTED);
    }

}
