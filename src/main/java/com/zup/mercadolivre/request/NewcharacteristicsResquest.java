package com.zup.mercadolivre.request;

import com.zup.mercadolivre.model.Characteristics;
import com.zup.mercadolivre.model.Product;

import javax.validation.constraints.NotBlank;

public class NewcharacteristicsResquest {
    @NotBlank
    private String name;
    @NotBlank
    private String value;

    public NewcharacteristicsResquest(@NotBlank String name, @NotBlank String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Characteristics toModel(Product product){
        return new Characteristics(name,value, product);
    }


}
