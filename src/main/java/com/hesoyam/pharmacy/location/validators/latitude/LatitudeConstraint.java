package com.hesoyam.pharmacy.location.validators.latitude;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LatitudeConstraint {
    String message() default "Invalid latitude value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
