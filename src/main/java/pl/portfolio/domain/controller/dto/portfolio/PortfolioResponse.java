package pl.portfolio.domain.controller.dto.portfolio;

import pl.portfolio.domain.model.Portfolio;

import java.util.List;

public record PortfolioResponse(PortfolioData portfolio, List<OtherPortfolioData> otherPortfolios) {

    static public PortfolioResponse forPortfolio(Portfolio portfolio, List<Portfolio> otherPortfolios) {
        List<AccountInPortfolioResponse> accounts = portfolio.getDirectAccounts().stream()
                .map(account ->
                        new AccountInPortfolioResponse(account.getName(), String.valueOf(account.getValuePercentage()), account.getId()))
                .toList();
        List<GroupInPortfolioResponse> groups = portfolio.getGroups().stream()
                .map(group ->
                        new GroupInPortfolioResponse(group.getName(), String.valueOf(group.getValuePercentage()), group.getId()))
                .toList();
        List<OtherPortfolioData> otherPortfolioData = otherPortfolios.stream()
                .map(o -> new OtherPortfolioData(o.getId(), o.getName(), o.getTestPortfolio()))
                .toList();
        return new PortfolioResponse(new PortfolioData(portfolio.getId(), portfolio.getName(), portfolio.getTestPortfolio(), accounts, groups), otherPortfolioData);
    }

    private record PortfolioData(Integer id, String name, Boolean testPortfolio, List<AccountInPortfolioResponse> accounts, List<GroupInPortfolioResponse> groups) {

    }

    private record OtherPortfolioData(Integer id, String name, Boolean testPortfolio) {

    }

}
