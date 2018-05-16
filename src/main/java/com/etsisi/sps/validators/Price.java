package com.etsisi.sps.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
//https://github.com/stevehanson/spring-mvc-validation/blob/masterhttps://github.com/stevehanson/spring-mvc-validation
@Documented
@Constraint(validatedBy = PriceConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price
{
    String message() default "{Price}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
