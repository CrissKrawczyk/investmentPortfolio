package pl.portfolio.domain.controller.dto.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.service.AccountKindService;
import pl.portfolio.domain.service.CurrencyService;

@Component
@Getter
@Setter
public class AccountRequest {
    private String name;
    private Integer valuePercentage;
    private Integer accountKind;
    private Integer currency;

    public Account toAccount(AccountKindService accountKindService, CurrencyService currencyService) {
        Account account = new Account();
        account.setName(name);
        account.setValuePercentage(valuePercentage);
        if (accountKind != null && accountKind > 0) {
            account.setKind(accountKindService.getKind(accountKind));
        }
        if (currency != null && currency > 0) {
            account.setCurrency(currencyService.getCurrency(currency));
        }
        return account;
    }

}
