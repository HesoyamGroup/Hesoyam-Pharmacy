package com.hesoyam.pharmacy.user.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
        //Initialize comment
    }

    @Override
    public boolean isValid(String phoneNumberField, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumberField == null) return false;
        if(phoneNumberField.length() == 0) return true; //Optional

        return matchesWithDifferentRegexes(phoneNumberField);
    }

    private boolean matchesWithDifferentRegexes(String phoneNumberField){
        return phoneNumberField.matches("^\\d{10}$")
                || phoneNumberField.matches("^(\\d{3}[- .]?){2}\\d{4}$")
                || phoneNumberField.matches("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
                || phoneNumberField.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    }
}
