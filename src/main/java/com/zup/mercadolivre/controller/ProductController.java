package com.zup.mercadolivre.controller;

import com.zup.mercadolivre.model.Product;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.repository.UserRepository;
import com.zup.mercadolivre.request.NewProductRequest;
import com.zup.mercadolivre.validations.UniqueNameCategoryValidator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UserRepository repository;

    @InitBinder

    public void init(WebDataBinder binder){
        binder.addValidators( new UniqueNameCategoryValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Product> createProduct(@RequestBody @Valid NewProductRequest request, Authentication authentication) {
        User owner = repository.findByLogin(authentication.name());//porGetName
        Product product = request.toModel(manager, owner);
        manager.persist(product);
        return ResponseEntity.status(201).body(product);
    }
}
