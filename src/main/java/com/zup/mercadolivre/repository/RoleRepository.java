package com.zup.mercadolivre.repository;

import com.zup.mercadolivre.model.ERole;
import com.zup.mercadolivre.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByname(ERole name);
}
