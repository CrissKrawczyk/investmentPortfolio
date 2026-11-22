package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.repository.AccountGroupRepository;

import java.util.List;

@Service
public class AccountGroupService {

    @Autowired
    private AccountGroupRepository accountGroupRepository;

    @Autowired
    private PortfolioService portfolioService;

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
        return new ValidatedObject<>(accountGroupRepository.save(group));
    }

    public AccountGroup getGroup(Integer id) {
        return accountGroupRepository.getReferenceById(id);
    }

    public List<AccountGroup> getPortfolioGroups(Portfolio portfolio) {
        return accountGroupRepository.findAllByPortfolio(portfolio);
    }

}
