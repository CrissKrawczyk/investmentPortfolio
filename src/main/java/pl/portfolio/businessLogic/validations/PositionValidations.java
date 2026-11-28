package pl.portfolio.businessLogic.validations;

import pl.portfolio.domain.model.Position;

import java.util.HashMap;
import java.util.Map;

public class PositionValidations {

    private final Position position;

    public PositionValidations(Position position) {
        this.position = position;
    }

    public Map<String, String> getPositionErrors() {
        Map<String, String> errors = new HashMap<>();
        new ValuePercentageValidator(position.getValuePercentage())
                .validate()
                .ifPresent(error -> errors.put("valuePercentage", error));
        return errors;
    }

}
