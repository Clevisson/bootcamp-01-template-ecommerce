package com.zup.mercadolivre.controller;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.request.newCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Category> createCategory(@RequestBody @Valid newCategoryRequest request) {
        Category category = request.toModel(manager);
        manager.persist(category);
        return ResponseEntity.status(201).build();
    }
}
