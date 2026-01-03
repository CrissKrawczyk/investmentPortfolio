package pl.portfolio.businessLogic;

import pl.portfolio.domain.controller.dto.portfolio.DataForDistributionCompute;
import pl.portfolio.domain.controller.dto.portfolio.DistributionObjectType;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataForDistributionResolver {

    private final Portfolio portfolio;

    public DataForDistributionResolver(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public DataForDistributionCompute getData() {
        List<AccountGroup> groups = portfolio.getGroups();
        ArrayList<DataForDistributionCompute> portfolioItems = new ArrayList<>(getGroupsData(groups));
        List<Account> accounts = portfolio.getDirectAccounts();
        portfolioItems.addAll(getAccountsData(accounts));
        return new DataForDistributionCompute(portfolio.getId(), "", DistributionObjectType.portfolio, 100, portfolioItems);
    }

    private List<DataForDistributionCompute> getGroupsData(List<AccountGroup> accounts) {
        return accounts.stream()
                .map(this::getDataForGroup)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<DataForDistributionCompute> getAccountsData(List<Account> accounts) {
        return accounts.stream()
                .map(this::getDataForAccount)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<DataForDistributionCompute> getDataForGroup(AccountGroup group) {
        int valuePercentage = group.getValuePercentage();
        if (valuePercentage <= 0) {
            return Optional.empty();
        }
        List<Account> accounts = group.getAccounts();
        List<DataForDistributionCompute> items = getAccountsData(accounts);
        DataForDistributionCompute positionData = DataForDistributionCompute.forGroup(group, items);
        return Optional.of(positionData);
    }

    private Optional<DataForDistributionCompute> getDataForAccount(Account account) {
        int valuePercentage = account.getValuePercentage();
        if (valuePercentage <= 0) {
            return Optional.empty();
        }
        List<Position> positions = account.getPositions();
        List<DataForDistributionCompute> items = getDataForPositions(positions);
        DataForDistributionCompute positionData = DataForDistributionCompute.forAccount(account, items);
        return Optional.of(positionData);
    }

    private List<DataForDistributionCompute> getDataForPositions(List<Position> positions) {
        return positions.stream()
                .map(this::getDataForPosition)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<DataForDistributionCompute> getDataForPosition(Position position) {
        int valuePercentage = position.getValuePercentage();
        if (valuePercentage <= 0) {
            return Optional.empty();
        }
        DataForDistributionCompute positionData = DataForDistributionCompute.forPosition(position);
        return Optional.of(positionData);
    }

    private void checkFor100PercentInItems(DataForDistributionCompute parent) {
        List<DataForDistributionCompute> items = parent.items();
        if (items.isEmpty()) {
            return;
        }
        int sum = 0;
        for (DataForDistributionCompute item : items) {
            sum += item.percentageValue();
        }
        if (sum != 100) {
            throw new No100PercentItemsForDistribution(parent);
        }
    }

}
