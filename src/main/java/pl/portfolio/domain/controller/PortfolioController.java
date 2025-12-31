package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.controller.dto.portfolio.AccountInPortfolioResponse;
import pl.portfolio.domain.controller.dto.portfolio.GroupInPortfolioResponse;
import pl.portfolio.domain.controller.dto.portfolio.PortfolioResponse;
import pl.portfolio.domain.service.AccountGroupService;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.PortfolioService;

import java.util.List;

@Controller
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountGroupService groupService;

    @GetMapping
    public ResponseEntity<PortfolioResponse> getUserPortfolio() {
        Portfolio currentUserPortfolio = portfolioService.getCurrentUserPortfolio();
        List<AccountInPortfolioResponse> accounts = accountService.getPortfolioAccounts(currentUserPortfolio).stream()
                .map(account ->
                        new AccountInPortfolioResponse(account.getName(), String.valueOf(account.getValuePercentage()), account.getId()))
                .toList();
        List<GroupInPortfolioResponse> groups = groupService.getPortfolioGroups(currentUserPortfolio).stream()
                .map(group ->
                        new GroupInPortfolioResponse(group.getName(), String.valueOf(group.getValuePercentage()), group.getId()))
                .toList();
        return new ResponseEntity<>(new PortfolioResponse(accounts, groups), HttpStatus.ACCEPTED);
    }

}
