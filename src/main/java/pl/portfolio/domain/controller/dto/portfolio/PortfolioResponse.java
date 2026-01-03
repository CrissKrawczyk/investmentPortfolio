package pl.portfolio.domain.controller.dto.portfolio;

import pl.portfolio.domain.model.Portfolio;

import java.util.List;

public record PortfolioResponse(List<AccountInPortfolioResponse> accounts, List<GroupInPortfolioResponse> groups) {

    static public PortfolioResponse forPortfolio(Portfolio portfolio) {
        List<AccountInPortfolioResponse> accounts = portfolio.getDirectAccounts().stream()
                .map(account ->
                        new AccountInPortfolioResponse(account.getName(), String.valueOf(account.getValuePercentage()), account.getId()))
                .toList();
        List<GroupInPortfolioResponse> groups = portfolio.getGroups().stream()
                .map(group ->
                        new GroupInPortfolioResponse(group.getName(), String.valueOf(group.getValuePercentage()), group.getId()))
                .toList();
        return new PortfolioResponse(accounts, groups);
    }

}
