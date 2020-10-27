package com.zup.mercadolivre.model;

import com.zup.mercadolivre.request.NewcharacteristicsResquest;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private BigDecimal value;
    @NotNull
    @Positive
    private int quantity;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<Characteristics> characteristics = new HashSet<>();
    @NotBlank
    @Length(max = 1000)
    private String description;
    @ManyToOne
    @NotNull
    @Valid
    private Category category;
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Valid
    @ManyToOne
    private User owner;

    @Deprecated
    public Product() {
    }

    public Product(@NotBlank String name,
                   @Size(min = 3)
                   @NotNull @Positive BigDecimal value,
                   @NotNull @Min(0) int quantity,
                   @Size(min = 3) @Valid Collection<NewcharacteristicsResquest> characteristics,
                   @NotBlank @Length(max = 1000) String description,
                   @NotNull Category category, User owner) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.characteristics.addAll(characteristics.stream().map(characteristic -> characteristic.toModel(this)).collect(Collectors.toSet()));
        Assert.isTrue(this.characteristics.size() >= 3, "Produto tem que ter 3 caracteristicas ou mais");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Set<Characteristics> getCharacteristics() {
        return characteristics;
    }
}
