package com.zup.mercadolivre.validations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validation;
import java.util.List;

public class UniqueIdValidator implements ConstraintValidator<UniqueId, Object> {
    private  String domainAttribute;
    private Class<?> kclass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueId params) {
            domainAttribute = params.fieldName();
            kclass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery ("select 1 from "+kclass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> resultList = query.getResultList();
        Assert.isTrue(resultList.size() <= 1, "Houve um probe=lema de inconsistência e tem mais de um " +kclass + "com o mesmo " + domainAttribute + "com valor = " +value);
        return !resultList.isEmpty();
    }
}
