package com.zup.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String value;

    @ManyToOne
    private Product product;

    @Deprecated
    public Characteristics() {

    }

    public Characteristics(@NotBlank String name, @NotBlank String value, Product product) {
        this.name = name;
        this.value = value;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Characteristics other = (Characteristics) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }


}
