package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.AccountValidations;
import pl.portfolio.businessLogic.validations.GroupValidations;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.AccountGroupRepository;

import java.util.List;
import java.util.Map;

@Service
public class AccountGroupService {

    @Autowired
    private AccountGroupRepository accountGroupRepository;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    public ValidatedObject<AccountGroup> createAccountGroup(AccountGroup group, Integer groupId, Integer portfolioId) {
        if (groupId != null) {
            AccountGroup parent = accountGroupRepository.getReferenceById(groupId);
            group.setGroup(parent);
        } else if (portfolioId != null) {
            Portfolio parent = portfolioService.getPortfolio(portfolioId);
            group.setPortfolio(parent);
        } else {
            group.setPortfolio(portfolioService.getCurrentUserPortfolio());
        }
        GroupValidations groupValidations = new GroupValidations(group);
        Map<String, String> errors = groupValidations.getGroupErrors();
        if (!errors.isEmpty()) {
            return new ValidatedObject<>(group, errors);
        }
        return new ValidatedObject<>(accountGroupRepository.save(group));
    }

    public AccountGroup getGroup(Integer id) {
        return accountGroupRepository.getReferenceById(id);
    }

    public List<AccountGroup> getPortfolioGroups(Portfolio portfolio) {
        return accountGroupRepository.findAllByPortfolio(portfolio);
    }

    public void deleteGroup(Integer id) {
        User currentUser = userService.getCurrentUser();
        AccountGroup group = getGroup(id);
        Integer portfolioUserId = findGroupOwner(group).getId();
        if (portfolioUserId.equals(currentUser.getId())) {
            accountGroupRepository.delete(group);
        } else {
            throw new RuntimeException("Unauthorized group deletion");
        }
    }

    private User findGroupOwner(AccountGroup accountGroup) {
        Portfolio portfolio = accountGroup.getPortfolio();
        if (portfolio != null) {
            return portfolio.getOwner();
        }
        AccountGroup parent = accountGroup.getGroup();
        return findGroupOwner(parent);
    }

}
