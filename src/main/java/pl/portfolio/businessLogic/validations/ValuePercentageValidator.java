package pl.portfolio.businessLogic.validations;

import java.util.Optional;

public class ValuePercentageValidator {

    private final Integer valuePercentage;

    public ValuePercentageValidator(Integer valuePercentage) {
        this.valuePercentage = valuePercentage;
    }

    public Optional<String> validate() {
        if (valuePercentage == null) {
            return Optional.empty();
        }
        if (valuePercentage > 100 || valuePercentage < 0) {
            return Optional.of("outOfRange");
        }
        return Optional.empty();
    }

}
