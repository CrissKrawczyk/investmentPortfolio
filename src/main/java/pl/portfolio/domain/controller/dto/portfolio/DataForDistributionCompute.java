package pl.portfolio.domain.controller.dto.portfolio;

import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.Position;

import java.util.ArrayList;
import java.util.List;

public record DataForDistributionCompute(Integer id, String name, DistributionObjectType type, int percentageValue,
                                         List<DataForDistributionCompute> items) {

    static public DataForDistributionCompute forPosition(Position position) {
        Integer id = position.getId();
        int valuePercentage = position.getValuePercentage();
        return new DataForDistributionCompute(id, position.getName(), DistributionObjectType.position, valuePercentage, new ArrayList<>());
    }

    static public DataForDistributionCompute forAccount(Account account, List<DataForDistributionCompute> items) {
        Integer id = account.getId();
        int valuePercentage = account.getValuePercentage();
        return new DataForDistributionCompute(id, account.getName(), DistributionObjectType.account, valuePercentage, items);
    }

    static public DataForDistributionCompute forGroup(AccountGroup group, List<DataForDistributionCompute> items) {
        Integer id = group.getId();
        int valuePercentage = group.getValuePercentage();
        return new DataForDistributionCompute(id, group.getName(), DistributionObjectType.group, valuePercentage, items);
    }

}
