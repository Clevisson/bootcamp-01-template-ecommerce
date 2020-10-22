package com.zup.mercadolivre.repository;

import com.zup.mercadolivre.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByLogin(String login);
}
