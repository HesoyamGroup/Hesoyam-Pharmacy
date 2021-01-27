package com.hesoyam.pharmacy.location.validators.latitude;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<LatitudeConstraint, Double> {
    @Override
    public boolean isValid(Double latitude, ConstraintValidatorContext constraintValidatorContext) {
        return ( latitude != null && latitude >= -90 && latitude <= 90 );
    }
}
