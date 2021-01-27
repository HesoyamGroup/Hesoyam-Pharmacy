package com.hesoyam.pharmacy.location.validators.longitude;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<LongitudeConstraint, Double> {
    @Override
    public boolean isValid(Double longitude, ConstraintValidatorContext constraintValidatorContext) {
        return (longitude != null && longitude >= -180 && longitude <= 180);
    }
}
