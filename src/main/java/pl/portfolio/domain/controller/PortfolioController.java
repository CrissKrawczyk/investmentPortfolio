package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.webApiModels.AccountResponse;
import pl.portfolio.domain.model.webApiModels.PortfolioResponse;
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

    @GetMapping
    public ResponseEntity<PortfolioResponse> getUserPortfolio() {
        Portfolio currentUserPortfolio = portfolioService.getCurrentUserPortfolio();
        List<AccountResponse> accounts = accountService.getPortfolioAccounts(currentUserPortfolio).stream()
                .map(account ->
                        new AccountResponse(account.getName(), String.valueOf(account.getValuePercentage())))
                .toList();
        return new ResponseEntity<>(new PortfolioResponse(accounts), HttpStatus.ACCEPTED);
    }

}
