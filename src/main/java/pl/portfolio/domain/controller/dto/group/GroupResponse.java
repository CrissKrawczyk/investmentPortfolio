package pl.portfolio.domain.controller.dto.group;

import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;

import java.util.ArrayList;
import java.util.List;

public record GroupResponse(String name, Integer id, List<AccountInGroupResponse> accounts, List<GroupInGroupResponse> groups) {

    static public GroupResponse forGroup(AccountGroup group) {
        List<Account> accounts = group.getAccounts();
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        List<AccountInGroupResponse> accountsInGroup = accounts.stream()
                .map(account ->
                        new AccountInGroupResponse(account.getName(), account.getValuePercentage(), account.getId()))
                .toList();

        List<AccountGroup> groups = group.getGroups();
        if (groups == null) {
            groups = new ArrayList<>();
        }
        List<GroupInGroupResponse> groupsInGroup = groups.stream()
                .map(childGroup ->
                        new GroupInGroupResponse(childGroup.getName(), childGroup.getValuePercentage(), childGroup.getId()))
                .toList();

        return new GroupResponse(group.getName(), group.getId(), accountsInGroup, groupsInGroup);
    }

    private record AccountInGroupResponse(String name, Integer valuePercentage, Integer id) {
    }

    private record GroupInGroupResponse(String name, Integer valuePercentage, Integer id) {
    }

}
