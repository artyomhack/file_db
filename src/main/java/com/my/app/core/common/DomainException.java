package com.my.app.core.common;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }

    static public class Validation extends DomainException {
        @Getter
        private final Map<String, ?> errors;

        public Validation(Set<ConstraintViolation<?>> violations) {
            super("Validation error");
            this.errors = violations.stream().collect(Collectors.toMap(
                    it -> it.getPropertyPath().toString(),
                    ConstraintViolation::getMessage
            ));
        }
    }
}
