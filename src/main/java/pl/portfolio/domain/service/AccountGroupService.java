package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public AccountGroup createAccountGroup(AccountGroup group) {
        if (group.getPortfolio() == null) {
            group.setPortfolio(portfolioService.getCurrentUserPortfolio());
        }
        return accountGroupRepository.save(group);
    }

    public AccountGroup getGroup(Integer id) {
        return accountGroupRepository.getReferenceById(id);
    }

    public List<AccountGroup> getPortfolioGroups(Portfolio portfolio) {
        return accountGroupRepository.findAllByPortfolio(portfolio);
    }

}
