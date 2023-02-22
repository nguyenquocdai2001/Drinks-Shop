package com.demo.coffee.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.coffee.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	void deleteById(Integer categoryID);
}
