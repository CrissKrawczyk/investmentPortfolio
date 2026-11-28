package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.controller.dto.account.AccountRequest;
import pl.portfolio.domain.controller.dto.account.AccountResponse;
import pl.portfolio.domain.controller.dto.group.GroupResponse;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.service.AccountGroupService;
import pl.portfolio.domain.service.AccountKindService;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.CurrencyService;

import java.util.Optional;

@Controller
@RequestMapping("/api/accountGroup")
public class AccountGroupController {

    @Autowired
    AccountGroupService accountGroupService;
    @Autowired
    AccountKindService accountKindService;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<ValidatedObject<GroupResponse>> createGroup(@RequestBody AccountGroup group,
                                                                      @RequestParam Optional<Integer> groupId,
                                                                      @RequestParam Optional<Integer> portfolioId) {
        ValidatedObject<AccountGroup> savedGroup = accountGroupService.createAccountGroup(group, groupId.orElse(null), portfolioId.orElse(null));
        GroupResponse groupResponse = GroupResponse.forGroup(savedGroup.getObject());
        return new ResponseEntity<>(savedGroup.forNewObject(groupResponse), savedGroup.getStatusByErrors(HttpStatus.CREATED));
    }

    @PostMapping
    @RequestMapping("/{id}/addAccount")
    public ResponseEntity<ValidatedObject<AccountResponse>> addAccount(@RequestBody AccountRequest accountRequest, @PathVariable Integer id) {
        Account account = accountRequest.toAccount(accountKindService, currencyService);
        AccountGroup group = accountGroupService.getGroup(id);
        account.setGroup(group);
        ValidatedObject<Account> savedAccount = accountService.createAccount(account);
        AccountResponse accountResponse = AccountResponse.fromAccount(savedAccount.getObject());
        return new ResponseEntity<>(savedAccount.forNewObject(accountResponse), savedAccount.getStatusByErrors(HttpStatus.CREATED));
    }

    @GetMapping("{id}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable("id") Integer id) {
        AccountGroup group = accountGroupService.getGroup(id);
        return new ResponseEntity<>(GroupResponse.forGroup(group), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{groupId}")
    public ResponseEntity<Void> deleteTestPortfolio(@PathVariable("groupId") Integer groupId) {
        accountGroupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
