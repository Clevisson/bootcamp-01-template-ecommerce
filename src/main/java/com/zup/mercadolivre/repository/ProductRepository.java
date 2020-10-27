package com.zup.mercadolivre.repository;

import com.zup.mercadolivre.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
