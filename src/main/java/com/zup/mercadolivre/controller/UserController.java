package com.zup.mercadolivre.controller;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.request.newUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody @Valid newUserRequest request) {
        User user = request.toModel();
        manager.persist(user);
        return ResponseEntity.status(201).build();
    }
}

