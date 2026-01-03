package pl.portfolio.domain.controller.dto.group;

import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;

import java.util.ArrayList;
import java.util.List;

public record GroupResponse(String name, List<AccountInGroupResponse> accounts) {

    static public GroupResponse forGroup(AccountGroup group) {
        List<Account> accounts = group.getAccounts();
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        List<AccountInGroupResponse> accountsInGroup = accounts.stream()
                .map(account ->
                        new AccountInGroupResponse(account.getName(), account.getValuePercentage(), account.getId()))
                .toList();
        return new GroupResponse(group.getName(), accountsInGroup);
    }

}
