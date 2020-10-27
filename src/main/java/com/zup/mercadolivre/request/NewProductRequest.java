package com.zup.mercadolivre.request;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.Product;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.validations.UniqueId;
import com.zup.mercadolivre.validations.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NewProductRequest {
    @UniqueValue(domainClass = Product.class, fieldName = "name")
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private BigDecimal value;
    @NotNull
    @Positive
    private int quantity;
    @Size(min = 3)
    @Valid
    private List<NewcharacteristicsResquest> characteristics = new ArrayList<>();
    @NotBlank
    @Size(max = 1000)
    private String description;
    @UniqueId(domainClass = Category.class, fieldName = "id")
    private Long idCategory;

    public NewProductRequest(@NotBlank String name,
                             @NotNull @Min(1) BigDecimal value,
                             @NotNull @Positive int quantity,
                             List<NewcharacteristicsResquest> characteristics,
                             @NotBlank @Length(max = 1000) String description,
                             Long idCategory) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.characteristics.addAll(characteristics);
        this.description = description;
        this.idCategory = idCategory;
    }

    public void setCharacteristics(List<NewcharacteristicsResquest> characteristics) {
        this.characteristics = characteristics;
    }

    public List<NewcharacteristicsResquest> getCharacteristics() {
        return characteristics;
    }

    public Product toModel(EntityManager manager, User owner) {
        Category category = manager.find(Category.class, idCategory);
        return new Product(name, value, quantity, characteristics, description, category, owner);
    }


    public boolean hasCharacteristicsEqual() {
        HashSet<String> equalName = new HashSet<>();
        for (NewcharacteristicsResquest characteristic : characteristics) {
            if (!equalName.add(characteristic.getName())) {
                return true;
            }
        }
        return false;
    }
}
