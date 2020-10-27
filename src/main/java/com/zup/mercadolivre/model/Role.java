package com.zup.mercadolivre.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole nameRole;

    @Deprecated
    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getNameRole() {
        return nameRole;
    }

    public void setNameRole(ERole nameRole) {
        this.nameRole = nameRole;
    }
}
