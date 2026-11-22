package pl.portfolio.businessLogic.validations;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ValidatedObject<T> {
    @Getter
    private T object;
    @Getter
    private Map<String, String> errors;

    public ValidatedObject(T object) {
        this.object = object;
        this.errors = new HashMap<>();
    }

    public ValidatedObject(T object, Map<String, String> errors) {
        this.object = object;
        this.errors = errors;
    }

    public void addError(String field, String error) {
        if (!error.isEmpty()) {
            errors.put(field, error);
        }
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public HttpStatus getStatusByErrors(HttpStatus success) {
        if (isValid()) {
            return success;
        }
        return HttpStatus.OK;
    }

    public <O> ValidatedObject<O> forNewObject(O other) {
        return new ValidatedObject<O>(other, errors);
    }

}
