package ph.eraine.poc.multipleauth.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {

    private final Map<String, String> errors;

    public ValidationException(String message) {
        super(message);
        this.errors = new HashMap<>();
    }

    public ValidationException(String code, String message) {
        super(message);
        this.errors = new HashMap<>();
        addError(code, message);
    }

    public void addError(String code, String message) {
        errors.put(code, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}