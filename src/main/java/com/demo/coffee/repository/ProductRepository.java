package com.demo.coffee.repository;



import org.springframework.data.repository.CrudRepository;

import com.demo.coffee.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Iterable<Product> findByCategoryID(Integer categoryID);
}
