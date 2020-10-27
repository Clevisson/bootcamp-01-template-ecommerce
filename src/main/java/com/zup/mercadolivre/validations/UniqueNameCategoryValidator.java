package com.zup.mercadolivre.validations;

import com.zup.mercadolivre.request.NewProductRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UniqueNameCategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewProductRequest.class.isAssignableFrom(aClass);

    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
    NewProductRequest request = (NewProductRequest) target;
        if(request.hasCharacteristicsEqual()){
            errors.rejectValue("characteristics", null, "Existem caracteristicas igual");
        }
    }
}
