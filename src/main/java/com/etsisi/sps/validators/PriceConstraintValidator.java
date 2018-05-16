package com.etsisi.sps.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceConstraintValidator implements ConstraintValidator<Price,String>
{

    @Override
    public void initialize(Price constraintAnnotation) {

    }

    @Override
    public boolean isValid(String priceField, ConstraintValidatorContext cvc) {
        if (priceField==null) {
            return false;
        }
        return priceField.matches("[0-9]*\\.?[0-9]+");
    }
}
