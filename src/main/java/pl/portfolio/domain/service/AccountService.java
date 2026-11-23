package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.AccountValidations;
import pl.portfolio.businessLogic.validations.UserValidations;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.repository.AccountRepository;

import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PortfolioService portfolioService;

    public ValidatedObject<Account> createAccount(Account account) {
        if (account.getGroup() == null && account.getPortfolio() == null) {
            account.setPortfolio(portfolioService.getCurrentUserPortfolio());
        }
        AccountValidations accountValidations = new AccountValidations(account);
        Map<String, String> errors = accountValidations.getAccountErrors();
        if (!errors.isEmpty()) {
            return new ValidatedObject<>(account, errors);
        }
        return new ValidatedObject<>(accountRepository.save(account));
    }

    public List<Account> getPortfolioAccounts(Portfolio portfolio) {
        return accountRepository.findAllByPortfolio(portfolio);
    }

    public List<Account> getGroupAccounts(AccountGroup group) {
        return accountRepository.findAllByGroup(group);
    }

    public Account getAccount(Integer id) {
        return accountRepository.getReferenceById(id);
    }

}
