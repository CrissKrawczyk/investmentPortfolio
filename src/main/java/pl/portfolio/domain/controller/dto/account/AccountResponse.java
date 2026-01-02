package pl.portfolio.domain.controller.dto.account;

import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.Position;

import java.util.ArrayList;
import java.util.List;

public record AccountResponse(Integer id, String name, int valuePercentage, List<PositionInAccount> positions) {

    static public AccountResponse fromAccount(Account account) {
        List<Position> positions = account.getPositions();
        if (positions == null) {
            positions = new ArrayList<>();
        }
        List<PositionInAccount> positionsInAccount = positions.stream()
                .map(p -> new PositionInAccount(p.getId(), p.getName()))
                .toList();
        return new AccountResponse(account.getId(), account.getName(), account.getValuePercentage(), positionsInAccount);
    }

}
