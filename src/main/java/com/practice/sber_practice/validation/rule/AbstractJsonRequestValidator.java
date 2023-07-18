package com.practice.sber_practice.validation.rule;

import com.practice.sber_practice.pojo_scheme.request.ServiceRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractJsonRequestValidator implements Validator {
    private static final Class<?> VALIDATED_CLASS = ServiceRequest.class;

    protected Validator next; // The next element in the chain of responsibility

    public Validator setNext(Validator validator) {
        if (!validator.supports(VALIDATED_CLASS)) {
            throw new IllegalArgumentException("The supplied validator " + validator.getClass().getSimpleName() +
                    " does not support validation of " + VALIDATED_CLASS.getSimpleName() + " instances.");
        }
        next = validator;
        return validator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return VALIDATED_CLASS.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (needToApplyValidator((ServiceRequest) target)) {
            validateRequest((ServiceRequest) target, errors);
        }

        if (next != null) {
            next.validate(target, errors);
        }
    }

    protected abstract boolean needToApplyValidator(ServiceRequest serviceRequest);

    protected abstract void validateRequest(ServiceRequest serviceRequest, Errors errors);
}
