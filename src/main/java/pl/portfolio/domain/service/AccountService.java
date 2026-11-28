package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.AccountValidations;
import pl.portfolio.businessLogic.validations.UserValidations;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.AccountRepository;

import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

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

    public void deleteAccount(Integer id) {
        User currentUser = userService.getCurrentUser();
        Account account = getAccount(id);
        Integer portfolioUserId = findAccountOwner(account).getId();
        if (portfolioUserId.equals(currentUser.getId())) {
            accountRepository.delete(account);
        } else {
            throw new RuntimeException("Unauthorized account deletion");
        }
    }

    private User findAccountOwner(Account account) {
        Portfolio portfolio = account.getPortfolio();
        if (portfolio != null) {
            return portfolio.getOwner();
        }
        return findAccountOwner(account.getGroup());
    }

    private User findAccountOwner(AccountGroup accountGroup) {
        Portfolio portfolio = accountGroup.getPortfolio();
        if (portfolio != null) {
            return portfolio.getOwner();
        }
        AccountGroup parent = accountGroup.getGroup();
        return findAccountOwner(parent);
    }

}
