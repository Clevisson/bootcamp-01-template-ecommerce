package com.zup.mercadolivre.controller;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.repository.UserRepository;
import com.zup.mercadolivre.request.newUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody @Valid newUserRequest request) {
        User user = request.toModel();
        manager.persist(user);
        return ResponseEntity.status(201).build();
    }
    @GetMapping
    public Iterable<User> listUser(){
        return repository.findAll();
    }
}