package com.zup.mercadolivre.request;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.validations.UniqueId;
import com.zup.mercadolivre.validations.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class newCategoryRequest {
    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    @Positive
    @UniqueId(domainClass = Category.class, fieldName = "id")
    private Long idMotherCategory;

    @Deprecated
    public newCategoryRequest() {

    }

    public newCategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public Category toModel(EntityManager manager) {
        Category category = new Category(name);
        if (idMotherCategory != null) {
            Category motherCategory = manager.find(Category.class, idMotherCategory);
            category.setMotherCategory(motherCategory);
        }
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdMotherCategory(Long idMotherCategory) {
        this.idMotherCategory = idMotherCategory;
    }
}
