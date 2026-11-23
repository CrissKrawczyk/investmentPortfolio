package pl.portfolio.businessLogic.validations;

import pl.portfolio.domain.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountValidations {

    private final Account account;

    public AccountValidations(Account account) {
        this.account = account;
    }

    public Map<String, String> getAccountErrors() {
        Map<String, String> errors = new HashMap<>();
        new ValuePercentageValidator(account.getValuePercentage())
                .validate()
                .ifPresent(error -> errors.put("valuePercentage", error));
        return errors;
    }

}
