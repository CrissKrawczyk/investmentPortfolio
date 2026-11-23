package pl.portfolio.businessLogic.validations;

import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupValidations {

    private final AccountGroup accountGroup;

    public GroupValidations(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }

    public Map<String, String> getGroupErrors() {
        Map<String, String> errors = new HashMap<>();
        new ValuePercentageValidator(accountGroup.getValuePercentage())
                .validate()
                .ifPresent(error -> errors.put("valuePercentage", error));
        return errors;
    }

}
