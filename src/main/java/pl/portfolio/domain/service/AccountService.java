package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PortfolioService portfolioService;

    public Account createAccount(Account account) {
        if (account.getGroup() == null && account.getPortfolio() == null) {
            account.setPortfolio(portfolioService.getCurrentUserPortfolio());
        }
        return accountRepository.save(account);
    }

    public List<Account> getPortfolioAccounts(Portfolio portfolio) {
        return accountRepository.findAllByPortfolio(portfolio);
    }

}
