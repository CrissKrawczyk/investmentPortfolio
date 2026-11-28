package pl.portfolio.domain.controller.dto.position;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.Position;
import pl.portfolio.domain.model.PositionKind;
import pl.portfolio.domain.service.AccountKindService;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.CurrencyService;

@Component
@Getter
@Setter
public class PositionRequest {
    private String name;
    private Integer valuePercentage;
    private String kind;
    private Integer currency;
    private Integer accountId;

    public Position toPosition(CurrencyService currencyService, AccountService accountService) {
        Position position = new Position();
        position.setName(name);
        position.setValuePercentage(valuePercentage);
        if (kind != null && !kind.isEmpty()) {
            position.setKind(PositionKind.valueOf(kind));
        }
        if (currency != null && currency > 0) {
            position.setCurrency(currencyService.getCurrency(currency));
        }
        Account account = accountService.getAccount(accountId);
        position.setAccount(account);
        return position;
    }

}
