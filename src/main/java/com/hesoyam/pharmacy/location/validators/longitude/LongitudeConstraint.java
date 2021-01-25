package com.hesoyam.pharmacy.location.validators.longitude;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LongitudeConstraint {
    String message() default "Invalid longitude value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payLoad() default {};
}
